sudo docker build -t test .

port 8000 в браузере port 8080 в контейнере
sudo docker run -it -p 8000:8080 test


sudo docker images  список образов
sudo docker rmi ubuntu:22.04  удаление образа
sudo docker images -aq  показывает id образов
sudo docker rmi $(sudo docker images -aq)  удалит все репозитории

sudo docker ps -a  команды ранее введенные
docker start serene_moore  запускаем контейнер с именем
docker rm serene_moore  удаляем контейнер
docker ps -q  выведет id запущенных контейнеров
docker ps -a -q  выведет id всех контейнеров
sudo docker rm $(sudo docker ps -a -q) удаляет все остановленные контейнеры
docker stop b9e43c977d87  можно остановить по id контейнера

docker system df  смотрим занимаемый объем на диске
sudo docker system prune -af  очищаем диск от остановленных контейнеров 

sudo docker volume ls  смотрим локальное хранилище
sudo docker volume rm $(sudo docker volume ls -qf dangling=true)  удаляем все


Deploy на сервер:
чтобы запустить Dockerfile необходимо:
    docker build -t my-java-app .
    docker run -p 8080:8080 my-java-app
Проверяем работоспособность
Останавливаем

Сохраняем контейнер в файл (домашняя директория)
sudo docker save -o image.tar my-java-app

Копируем на сервер по SSH (~ означает сохраниь в домашнюю директорию)
sudo scp image.tar root@46.29.163.150:~

Заходим на наш сервер:
ssh root@46.29.163.150

Импортируем образ из файла на сервере:
docker load < image.tar

Запускаем контейнер на сервере:
docker run -p 8000:8080 my-app

Переходим по 46.29.163.150:8000/users
