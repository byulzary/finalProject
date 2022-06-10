# Wait to be sure that SQL Server came up
sleep 15s

# Run the setup script to create the DB and the schema in the DB
# Note: make sure that your password matches what is in the Dockerfile
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P PaSSw0rd! -d master -i init.sql


# fix windows eol
sed -i.bak 's/\r//' supermarket.csv
sed -i.bak 's/\r//' items.csv
sed -i.bak 's/\r//' supermarket_items.csv

/opt/mssql-tools/bin/bcp dbo.supermarket in ./supermarket.csv -S localhost -U sa -P PaSSw0rd! -d AndroidProj -c -t  ',' -F2
/opt/mssql-tools/bin/bcp dbo.items in ./items.csv -S localhost -U sa -P PaSSw0rd! -d AndroidProj -c -t  ',' -F2
/opt/mssql-tools/bin/bcp dbo.supermarket_items in ./supermarket_items.csv -S localhost -U sa -P PaSSw0rd! -d AndroidProj -c -t  ',' -F2

echo "DATABASE INITIALIZED"