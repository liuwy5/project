drop table if exists sys_user;
drop table if exists sys_organization;
drop table if exists sys_resource;
drop table if exists sys_role;

create table sys_user (
  id bigint auto_increment,
  organization_id bigint,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  role_ids varchar(100),
  locked bool default false,
  constraint pk_sys_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_user_username on sys_user(username);
create index idx_sys_user_organization_id on sys_user(organization_id);

create table sys_organization (
  id bigint auto_increment,
  name varchar(100),
  parent_id bigint,
  parent_ids varchar(100),
  available bool default false,
  constraint pk_sys_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_organization_parent_id on sys_organization(parent_id);
create index idx_sys_organization_parent_ids on sys_organization(parent_ids);


create table sys_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_sys_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on sys_resource(parent_id);
create index idx_sys_resource_parent_ids on sys_resource(parent_ids);

create table sys_role (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  resource_ids varchar(100),
  available bool default false,
  constraint pk_sys_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_role_resource_ids on sys_role(resource_ids);


DELIMITER ;
delete from sys_user;
delete from sys_role;
delete from sys_resource;
delete from sys_organization;

insert into sys_user values(1,1,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f', '1', false);
insert into sys_organization values(1, '总公司', 0, '0/', true);
insert into sys_organization values(2, '分公司1', 1, '0/1/', true);
insert into sys_organization values(3, '分公司2', 1, '0/1/', true);
insert into sys_organization values(4, '分公司11', 2, '0/1/2/', true);

insert into sys_resource values(1, '资源', 'menu', '', 0, '0/', '', true);

insert into sys_resource values(11, '组织机构管理', 'menu', '/organization', 1, '0/1/', 'organization:*', true);
insert into sys_resource values(12, '组织机构新增', 'button', '', 11, '0/1/11/', 'organization:create', true);
insert into sys_resource values(13, '组织机构修改', 'button', '', 11, '0/1/11/', 'organization:update', true);
insert into sys_resource values(14, '组织机构删除', 'button', '', 11, '0/1/11/', 'organization:delete', true);
insert into sys_resource values(15, '组织机构查看', 'button', '', 11, '0/1/11/', 'organization:view', true);

insert into sys_resource values(21, '用户管理', 'menu', '/user', 1, '0/1/', 'user:*', true);
insert into sys_resource values(22, '用户新增', 'button', '', 21, '0/1/21/', 'user:create', true);
insert into sys_resource values(23, '用户修改', 'button', '', 21, '0/1/21/', 'user:update', true);
insert into sys_resource values(24, '用户删除', 'button', '', 21, '0/1/21/', 'user:delete', true);
insert into sys_resource values(25, '用户查看', 'button', '', 21, '0/1/21/', 'user:view', true);

insert into sys_resource values(31, '资源管理', 'menu', '/resource', 1, '0/1/', 'resource:*', true);
insert into sys_resource values(32, '资源新增', 'button', '', 31, '0/1/31/', 'resource:create', true);
insert into sys_resource values(33, '资源修改', 'button', '', 31, '0/1/31/', 'resource:update', true);
insert into sys_resource values(34, '资源删除', 'button', '', 31, '0/1/31/', 'resource:delete', true);
insert into sys_resource values(35, '资源查看', 'button', '', 31, '0/1/31/', 'resource:view', true);

insert into sys_resource values(41, '角色管理', 'menu', '/role', 1, '0/1/', 'role:*', true);
insert into sys_resource values(42, '角色新增', 'button', '', 41, '0/1/41/', 'role:create', true);
insert into sys_resource values(43, '角色修改', 'button', '', 41, '0/1/41/', 'role:update', true);
insert into sys_resource values(44, '角色删除', 'button', '', 41, '0/1/41/', 'role:delete', true);
insert into sys_resource values(45, '角色查看', 'button', '', 41, '0/1/41/', 'role:view', true);

insert into sys_role values(1, 'admin', '超级管理员', '11,21,31,41', true);