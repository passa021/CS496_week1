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
   -  연락처 
2. 연락처 수정  
   연락처 수정에 대한 이야기
3. 연락처 삭제  
   연락처 삭제에 대한 이야기
   

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
   todo list 추가에 대한 이야기
2. todo list 제거  
3. todo list 위치 변경  
