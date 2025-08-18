# BookSearchExample

카카오 도서 API를 활용한 책 검색 애플리케이션입니다.  
클린 아키텍처 + MVI 패턴을 기반으로 구현하였습니다.  

---

## 사용 프레임워크

- **언어** : Kotlin  
- **아키텍처** : 클린 아키텍처, MVI 패턴  
  - 각 계층을 `data`, `domain`, `app(presentation)` 으로 분리  
  - 단방향 데이터 흐름을 통해 명확한 상태 관리  
- **UI** : Jetpack Compose, Navigation, Coil(Image 로딩)  
- **비동기 처리** : Coroutine, Flow  
- **DI** : Hilt  
- **DB** : Room (즐겨찾기 데이터 로컬 저장)  
- **API 통신** : Retrofit + OkHttp (카카오 도서 API 연동)  

---

## 프로젝트 구조

### data (데이터 계층)  
외부 데이터 소스(API, DB)에서 데이터를 가져와 도메인 계층과 연결  

- **datasource**
  - `local` : Room 기반 즐겨찾기 DB 관리 (`BookDao`, `Book`, `BookLocalDataSource`)  
  - `remote` : 카카오 도서 API 통신 (`SearchApi`, `BookRemoteDataSource`)  
- **di** : Database, Network, Repository 를 Hilt 로 주입받기 위한 모듈 정의  
- **dto** : API 응답 DTO  
- **mapper** : DTO → Entity 변환  
- **repository** : Repository 구현체  
  - `BookLocalDataSource`, `BookRemoteDataSource`를 통해 DB/API 데이터를 가져와 Entity로 변환  



### domain (도메인 계층)  
비즈니스 로직 

- **entity** : 비즈니스 모델 정의  
- **usecase** : 비즈니스 로직  
  - `SearchBookUseCase` : 책 검색  
  - `GetFavoriteBookListUseCase` : 즐겨찾기 조회  
  - `ToggleFavoriteUseCase` : 즐겨찾기 추가/삭제  
- **repository** : Repository 인터페이스 정의  



### app (프레젠테이션 계층)  
UI와 사용자 인터랙션 

- **base** :  
  - MVI 패턴을 위한 `BaseViewModel`, `BaseState`, `BaseEvent`, `BaseEffect` 인터페이스 정의  
- **mapper** : Entity → UiModel 변환  
- **model** : UI Model 정의  
- **ui** : Compose 기반 화면 컴포넌트 (Screen, Item)  

---

## 구현 포인트

### 1) MVI 기반 상태 관리
- 각 ViewModel에서 State를 단일 소스로 관리  
- 사용자 액션 → Event → 비즈니스 로직 실행 → State 변경 → UI 업데이트
- 단방향 데이터 흐름을 통해 상태 변화를 명확히 관리  



### 2) Room으로 즐겨찾기 데이터 저장
- 즐겨찾기 데이터는 Room DB에 저장  
- 검색/필터/정렬 기능을 SQL 쿼리로 정의  
- Flow를 활용하여 즐겨찾기 리스트를 실시간 반영



### 3) Flow를 사용한 검색 기능
- `SearchRequest`를 State에 담아 API 파라미터 및 DB 쿼리에 필요한 데이터 관리  
- 검색어 입력, 정렬/필터 변경, 페이징 시 `SearchRequest` 변경 → 최신 요청만 유지하여 비즈니스 로직 실행  
  - 검색 화면 : `SearchBookUseCase`  
  - 즐겨찾기 화면 : `GetFavoriteBookListUseCase`  
- 불필요한 호출 방지를 위해 debounce, flatMapLatest 적용  
- 예외 발생 시 `loadState = LoadState.Error` 로 전환하여 에러 화면 노출  



### 4) 확장성을 고려한 화면 단위 분리
- 검색 / 즐겨찾기 / 상세 화면의 ViewModel, State, Event를 독립적으로 설계  
  - 화면별 책임을 명확히 분리하여 추후 기능 확장이 용이  
- 공통적으로 사용되는 비즈니스 로직은 UseCase로 추출하여 중복 로직 제거  

---
