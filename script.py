import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT

#Creating database 
con = psycopg2.connect(dbname='postgres', user='postgres', host='localhost', password='1214161820')
con.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
cur = con.cursor()
cur.execute("CREATE DATABASE motortek; ")
print("DATABASE motortek has been successfully created")