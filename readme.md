# 몰입캠프 Week 1

고려대 컴퓨터공학과 김민채, 카이스트 전산학부 박도윤
#
## Abstraction
이번 프로젝트에서 저희는 연락처, 갤러리, todo list를 구현하였습니다. 
#
## PART 0. 개발환경
minSdkVersion: 26  
targetSdkVersion: 30  
buildToolsVersion "30.0.3"  
#
## PART 1. 연락처
recyclerview를 이용하여 연락처를 일렬로 배치하였습니다. 그리고 각각의 이름을 클릭하면 이름과 전화번호를 볼 수 있으며, 이를 수정할 수도 있게 하였습니다. SQL을 사용하여 임시로 DB를 구현하여 연락처와 이름에 대한 정보를 저장하고, 수정하는 기능을 구현하였습니다. 유연한 삭제를 위해 스와이프 동작을 구현하였으며, 코드는 다음 사이트에서 가져왔습니다. (https://stickode.tistory.com/268)
기능

1. 연락처 저장: 다른 Activity에서 연락처 정보를 입력하고, 이를 DB에 저장하여 반영  
   ![add_contact](https://user-images.githubusercontent.com/81007362/147925036-2f42863b-8243-4a16-b63f-f228e3cf196b.gif)
   
2. 연락처 수정: 연락처의 구체적인 정보를 볼 수 있는 Activity에서 연락처 정보를 수정  
   ![edit_contact](https://user-images.githubusercontent.com/81007362/147925145-7988d84b-d918-42c2-81d2-d7bdaf16629f.gif)
3. 연락처 삭제: 스와이프를 통해 연락처를 삭제  
   ![remove_contact](https://user-images.githubusercontent.com/81007362/147925228-6778317c-f695-4929-9862-d3a6d0ec638e.gif)
   

#
## PART 2. 갤러리
Gridview와 imageview를 이용하여 3개의 열을 가진 갤러리 형식으로 사진을 띄울 수 있게 하였습니다. 각 이미지의 썸네일을 터치할 경우, 확대하여 볼 수 있고 우측 상단의 X 버튼을 누르면 돌아갈 수 있습니다. 툴바 상단의 메뉴 버튼을 눌러 사진을 추가할 수 있습니다. SharedPreference와 Json Array를 이용하여 이미지 uri을 저장하여 다른 액티비티를 실행해도 데이터가 남아있도록 구현하였습니다.

기능
1. 기기 내 갤러리에서 사진 가져오기: 최대 10개까지 복수 선택하여 가져올 수 있습니다.
2. 카메라로 사진 찍어 가져오기


#
## PART 3. To Do List 
recyclerview를 이용하여 연락처를 일렬로 배치하였습니다. 추가된 todolist가 즉시 반영되고, 삭제될 수 있도록 하였으며, todolist를 체크할 시, 하단으로 내려가도록 하여 달성하지 못한 목표가 상단에 배치되도록 하였습니다. 또한 연락처와 같이 스와이프 동작을 통해 삭제를 할 수 있도록 하였습니다.

기능

1. tood list 추가: 상단의 입력창을 통해 todolist를 추가하는 것  
   ![add_todo](https://user-images.githubusercontent.com/81007362/147925094-4ce9ca23-2501-426d-8ba5-5fe9e509f70c.gif)
2. todo list 제거: 스와이프를 통해 todolist를 없앰  
   ![remove_todo](https://user-images.githubusercontent.com/81007362/147925255-8c096c62-139d-40b9-9735-95ee152c2e10.gif)
3. todo list 위치 변경: 목표 달성 시(체크박스 클릭 시) todolist가 하단으로 내려감   
   ![replace_todo](https://user-images.githubusercontent.com/81007362/147925260-aeeabf1a-6010-43d8-99ac-b929dfb1b904.gif)
