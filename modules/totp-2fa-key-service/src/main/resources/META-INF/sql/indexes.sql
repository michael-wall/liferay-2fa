create unique index IX_29DC4FD5 on totp_SecretKey (companyId, userId);
create index IX_EFF82C21 on totp_SecretKey (uuid_[$COLUMN_LENGTH:75$], companyId);