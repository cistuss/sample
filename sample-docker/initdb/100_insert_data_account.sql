insert into account (
    email_address,
    password,
    first_name,
    last_name,
    register_datetime,
    register_account_id,
    update_datetime,
    update_account_id
)
values
(
    'admin@sample.jp',
    '$2a$10$YmGrCPBGFJ/ikzZiG27FHuDuPNYFllxkErpTW8t2I5r.fFkMGuvJe',
    '管理者', 'システム',
    current_timestamp, null,
    current_timestamp, null
)
;