aws rds create-db-instance \
    --engine sqlserver-se \
    --db-instance-identifier mymsftsqlserver \
    --allocated-storage 200 \
    --db-instance-class db.t3.micro \
    --vpc-security-group-ids sg-081571ef9ce98e794 \
    --db-subnet-group mydbsubnetgroup \
    --master-username postgres \
    --master-user-password p0stgrespwd \
    --backup-retention-period 1