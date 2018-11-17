# Blocker_이더리움 네트워크를 활용한 선거관리 시스템

## 소개
- 이더리움 기반의 블록체인 기술을 기반으로 한 선거 관리 시스템을 구축하여 선거의 투명성을 확보한다.
- 투표 시 투표권자의 지문인식을 통해 신원확인을 하고 token을 발행하여 해당 토큰을 사용해 투표를 하는 편리하면서 강화된 보안을 갖춘 시스템을 구축한다.

## 작품의 개발 배경 및 필요성
- 최근 블록체인을 활용한 서비스에 대해 다양한 분야에서 관심이 부각되고 있음
- 특히 공공분야에서 대국민 서비스에 적용하여 국민 편의 증진 및 공공효율화에 기여할 수 있는 item에 대해서 관심이 많아지고 있음
- 매 선거마다 배포되는 선거 홍보물 및 투표, 개표 시 발생되는 비용 (후보자 1인당 평균 1억 2천만원)을 절감하는 이상적인 해결책.

## 작품의 특징 및 장점 
- 모바일 플랫폼을 통해 어디서든 손쉬운 투표가능.
- 투표 시스템에 블록체인 기술을 도입하여 편리하면서도 강화된 보안기능 구축.
- 투표 기간 내에 실시간으로 후보자 정보 및 선거 공약 등과 같은 투표에 필요한 정보를 제공하여 누구나 쉽고 빠르게 정보 확인 가능.
- 온라인 환경에서 진행되는 투표이므로 용지 오류, 오탈자에 의한 무효표, 의문표 등을 방지 가능. 
- 투표를 하기 위해 투표장에 갈 필요 없이 언제 어디서나 핸드폰만 있다면 간단한 지문인증 절차를 통해 투표 가능하고 시간이 절약 됨.
- 실시간으로 진행되며 집계되기 때문에 선거가 끝남과 동시에 개표 결과를 바로확인 가능. 즉 개표를 위한 인력을 줄이며 개표업무를 효율적으로 진행 가능.
- 투표 종료 시 후보자별, 소속별, 지역별 유권자 분류에 의한 투표 통계자료를 신속하고 정확하게 확인 가능.
- 앱을 통해 각 선거를 선택하여 여러 선거를 동시에 진행 가능.

## 기대효과
- 기존에 존재하지 않는 서비스로서, 선거 관리 시스템구축으로 투표율의 증진과 선거조작 이슈를 없애는 긍정적 효과 기대 가능 (투표의 투명성)
- 중앙선거관리위원회에 따르면 2018 지방선거에 투입되는 비용은 1조 700억원으   로, 정당·후보자 선거비용 및 보전·부담 비용은 약 5063억원으로 추정. 
- 이 중 일부가 홍보물 제작과 선거원의 건비 등으로 사용되는데 이렇게 과다 사용되는 홍보 비용을 어플리케이션을 통해 대폭 줄일 수 있음.
- 선거 준비과정 및 개표과정에서의 비용 대폭 감소 가능
- 앱을 통해 투표한 사람들에게 복권 토큰을 발행함으로써 자동으로 투표로또에 참여 가능하게 하고, 투표 종료 시 정해진 등수에게 절약된 투표 비용 중 일부를 지불하여 투표율 상승 기대 가능.
- 선거철 골칫거리 문제 중 하나가 홍보물 쓰레기인데 이 홍보물 쓰레기를 수거하여 인증 시 추첨을 통해 상품을 증정하여 홍보물 쓰레기 문제를 보다 완화 가능.


## 주요 적용 기술
### 안드로이드
RxJava 라이브러리를 활용한 MVVM패턴을 적용하여 앱 구축.
Fragment를 활용한 TabView 형식으로 화면구성.
Retrofit을 이용하여 서버와 통신 연동. 
           
### Back-End(Node.js, mariaDB)
- 사용자의 pw를 저장할 때 pdkdf2를 사용하여 자동으로 랜덤한 salt 값을 생성하고 salt 값과 pw값을 이용하여 hash 값을 만들어 저장하여 보안을 강화 하였다. 
- 여러명의 사용자가 같은 pw를 사용하더라도 랜덤한 salt 값을 생성하기 때문에 다이제스트 값이 달라질 수 밖에 없다.
- 정보의 자원을 uri에 표현하고 자원에 대한 행위는 HTTP Method(GET, POST,PUT, DELETE)으로 표현하여 RESTful하게 API를 설계했다.
- AWS의 EC2에 서버를 올려 배포하였고 이미지는 AWS의 S3에 관계형 데이터베이스는 AWS의 RDS에 mariadb로 구축하였다.
                                                                 
### 이더리움 플랫폼
- Visual Code 툴로 Truffle 프레임워크에서 스마트 계약(Solidity)을 작성.
- Geth 와 가나쉬를 이용하여 테스트.
                                                                               
### 모바일 Dapp
- 모바일 어플리케이션(Android) 을 통한 화면 구성.
- 이더리움 스마트 계약을 호출.


## Preview
| ![1](./readmeImage/1.png) | ![2](./readmeImage/2.png) | ![2-1](./readmeImage/2-1.png) | ![2-2](./readmeImage/2-2.png) | ![3](./readmeImage/3.png) |
|:---:|:---:|:---:|:---:|:---:|
| ![4](./readmeImage/4.png) | ![5](./readmeImage/5.png) | ![6](./readmeImage/6.png) | ![7](./readmeImage/7.png) | ![8](./readmeImage/8.png) |
| ![8-1](./readmeImage/8-1.png) | ![8-2](./readmeImage/8-2.png) | ![8-3](./readmeImage/8-3.png) | ![8-4](./readmeImage/8-4.png) | ![8-5](./readmeImage/8-5.png) |
| ![9](./readmeImage/9.png) | ![10](./readmeImage/10.png) | ![11](./readmeImage/11.png) | ![12](./readmeImage/12.png) | ![13](./readmeImage/13.png) |
| ![14](./readmeImage/14.png) | ![15](./readmeImage/15.png) ||||

## Preview_ video
[![IMAGE ALT TEXT](http://img.youtube.com/vi/KF16BYdMv58/0.jpg)](http://www.youtube.com/watch?v=KF16BYdMv58 )

## 팀원 소개
- Mentor: 홍민표
- Mentee : 김주현, 조현우, 최준영
![groupImage](groupImage.jpeg)
