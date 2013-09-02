insert into users(id, login, name, password) values
  (1, 'admin', 'Administrator', '30911f4f43a4641143603c465892c50e5a85d49628cf45b2ceff987beeeaf9b1f0a08fa1d1b8c86d'),
  (2, 'user', 'Simple User', 'b9ddf364206fb412d284b836da4d18e2e9e1f97700e2bbee032220fbc8e6c20827843cca6c9a3572')
;

insert into users_roles(users_id, roles_id) values
 (1,1),
 (2,2)
;