# 몰입캠프 Week 1

고려대 컴퓨터공학과 김민채, 카이스트 전산학부 박도윤
#
## Abstraction
이번 프로젝트에서 저희는 연락처, 갤러리, todo list를 구현하였습니다. 이 때, 연락처, todo list에서는 SQL를 활용하여 임시 DB를 제작하여 데이터를 저장, 수정, 삭제하는 것을 구현하였습니다. 갤러리에서는 ()
#
## PART 1. 연락처
파일

1. 실행 파일  
   addContact.java: 연락처를 저장하는 기능 구현  
   Contact_info.java: 연락처의 게부정보를 띄우고, 수정하기 위한 기능 구현  
   Fragment1.java: 연락처의 메인 화면에 대한 구성
   SimpleTextAdapter.java: 연락처의 메인 화면 속 각각의 연락처에 대한 기능 구현, 연락처 삭제에 대한 기능 구현

2. 부가 파일
   Contact.java: 연락처 정보를 담는 object
   DBHelper.java: 연락처 정보를 DB에 담기 위해 도움을 주는 Class  
   ItemTouchHelperListener.java: 아이템을 스와이프을 통해 삭제할 수 있도록 해주는 인터페이스  
   ItemTouchHelperCallback.java: 아이템을 스와이프을 통해 삭제할 수 있도록 해주는 기능 구현 (https://stickode.tistory.com/268 참조)  
   
   
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
파일
1. 실행 파일  
   Fragment3.java: todolist의 메인 화면 구성 및 todolist 추가 기능  
   TodoAdapter.java: todolist의 메인 화면 속 각각의 todolist에 대한 기능 구현, todolist 삭제에 대한 기능 구현
   
2. 부가 파일  
   Todolist.java: todolist 정보를 담는 object
   DBHelper3.java: todolist 정보를 DB에 담기 위해 도움을 주는 Class  
   temTouchHelperListener.java: 아이템을 스와이프을 통해 삭제할 수 있도록 해주는 인터페이스  
   ItemTouchHelperCallback.java: 아이템을 스와이프을 통해 삭제할 수 있도록 해주는 기능 구현 (https://stickode.tistory.com/268 참조)  
   


기능

1. tood list 추가  
   ![add_todo](https://user-images.githubusercontent.com/81007362/147925094-4ce9ca23-2501-426d-8ba5-5fe9e509f70c.gif)
2. todo list 제거  
   ![remove_todo](https://user-images.githubusercontent.com/81007362/147925255-8c096c62-139d-40b9-9735-95ee152c2e10.gif)
3. todo list 위치 변경  
   ![replace_todo](https://user-images.githubusercontent.com/81007362/147925260-aeeabf1a-6010-43d8-99ac-b929dfb1b904.gif)
