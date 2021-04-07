

create table B_MESSAGE (
MSG_SEQ integer primary key,
USER_ID varchar(20),
TITLE varchar(20),
DATA varchar(200),
REG_DT date,
UDT_DT date
);
commit;

insert into B_MESSAGE(
MSG_SEQ
,USER_ID
,TITLE
,DATA
,REG_DT
,UDT_DT
)values(
1
,'user'
,'title'
,'sampleData'
,datetime('now')
,datetime('now')
)
;
commit;

select *
from B_MESSAGE
;