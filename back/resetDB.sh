#!/bin/bash

db="seedling_dev"

while IFS= read -r -d '' sql; do
  echo "$sql"" -> "$db
  mysql -uroot -proot $db <"$sql"
done < <(find src/test/resources/sql/dev/ -name '*.sql' -print0)
echo "import $db done"
