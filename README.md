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


