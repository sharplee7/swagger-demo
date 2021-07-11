정의

Swagger UI란 Swagger 제품군 중 API Documentation과 관련된 기능을 제공하는 제품이다.
Swagger 홈페이지에 등록된 Swagger UI에 대한 설명은 다음과 같다.

"Swagger UI allows anyone — be it your development team or your end consumers — to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your OpenAPI (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation and client side consumption."

즉, Swagger UI를 적용하면 별도의 코드 추가 없이도 OpenAPI 규격에 맞게 API를 자동으로 시각화 시켜 줄 수 있다.

예제 환경
spring boot 2.5.2
gradle
swagger ui 2.9.2
ModelMapper 2.3.8

Gradle dependency 추가
implementation group: 'io.springfox', name: 'springfox-swagger2', version: '2.9.2'
implementation group: 'io.springfox', name: 'springfox-swagger-ui', version: '2.9.2'

참조 URL: https://sharplee7.tistory.com/48
