delete from tboard
where to_char(tboard_reg_dt, 'yyyy-MM-dd') = to_char(sysdate, 'yyyy-MM-dd');

