# 몰입캠프 Week 1

고려대 컴퓨터공학과 김민채, 카이스트 전산학부 박도윤
#
## Abstraction
이번 프로젝트에서 저희는 연락처, 갤러리, todo list를 구현하였습니다. 
#
## PART 1. 연락처
recyclerview를 이용하여 연락처를 일렬로 배치하였습니다. 그리고 각각의 이름을 클릭하면 이름과 전화번호를 볼 수 있으며, 이를 수정할 수도 있게 하였습니다. SQL을 사용하여 임시로 DB를 구현하여 연락처와 이름에 대한 정보를 저장하고, 수정하는 기능을 구현하였습니다. 유연한 삭제를 위해 스와이프 동작을 구현하였으며, 코드는 다음 사이트에서 가져왔습니다. (https://stickode.tistory.com/268)
기능

1. 연락처 저장  
   ![add_contact](https://user-images.githubusercontent.com/81007362/147925036-2f42863b-8243-4a16-b63f-f228e3cf196b.gif)
   
2. 연락처 수정  
   ![edit_contact](https://user-images.githubusercontent.com/81007362/147925145-7988d84b-d918-42c2-81d2-d7bdaf16629f.gif)
3. 연락처 삭제  
   ![remove_contact](https://user-images.githubusercontent.com/81007362/147925228-6778317c-f695-4929-9862-d3a6d0ec638e.gif)
   

#
## PART 2. 갤러리

#
## PART 3. To Do List 
recyclerview를 이용하여 연락처를 일렬로 배치하였습니다. 추가된 todolist가 즉시 반영되고, 삭제될 수 있도록 하였으며, todolist를 체크할 시, 하단으로 내려가도록 하여 달성하지 못한 목표가 상단에 배치되도록 하였습니다. 또한 연락처와 같이 스와이프 동작을 통해 삭제를 할 수 있도록 하였습니다.

기능

1. tood list 추가  
   ![add_todo](https://user-images.githubusercontent.com/81007362/147925094-4ce9ca23-2501-426d-8ba5-5fe9e509f70c.gif)
2. todo list 제거  
   ![remove_todo](https://user-images.githubusercontent.com/81007362/147925255-8c096c62-139d-40b9-9735-95ee152c2e10.gif)
3. todo list 위치 변경  
   ![replace_todo](https://user-images.githubusercontent.com/81007362/147925260-aeeabf1a-6010-43d8-99ac-b929dfb1b904.gif)