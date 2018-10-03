create table totp_SecretKey (
	uuid_ VARCHAR(75) null,
	secretKeyId LONG not null primary key,
	companyId LONG,
	userId LONG,
	secretKey VARCHAR(75) null
);