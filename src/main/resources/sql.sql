
##子查询
SELECT * FROM t_permission p WHERE id IN (
	SELECT rp.`permissionid` FROM t_role_permission rp WHERE rp.`roleid` IN (
		SELECT ur.`roleid` FROM t_user_role ur WHERE ur.`userid`=1
	)

);

##联合查询
SELECT DISTINCT p.* FROM t_permission p
	LEFT JOIN  t_role_permission rp ON rp.`permissionid`=p.`id`
	LEFT JOIN t_user_role ur ON ur.`roleid`=rp.`roleid`
	WHERE ur.`userid`=1