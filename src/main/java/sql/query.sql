SELECT t.NAME,
       sum(case when money > 0 then money else 0 end) as sum_of_deposits,
       sum(case when money < 0 then -money else 0 end) as sum_of_withdrawals
from transfers t
group BY t.NAME
order by t.NAME ASC;