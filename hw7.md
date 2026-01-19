1. Запустить Docker Desktop под администратором
2. Запустить IDE под администратором
3. minikube status
4. #  minikube start <- Выполнить команду, если в выводе предыдущей команды статус Stopped
5. kubectl create namespace mantogin-hw7
6. mvn clean install -U <- собрать приложения
7. cd .\billing-app\
8. docker build --platform linux/amd64 -t mantogin/billing-app:1.0.0 . <- сборка для заливки на dockerhub
9. docker image push mantogin/billing-app:1.0.0 <- размещение образа на dockerhub
10. kubectl apply -f .\kuber\ -n mantogin-hw7
11. cd ..\notifications-app\
12. docker build --platform linux/amd64 -t mantogin/notifications-app:1.0.0 . <- сборка для заливки на dockerhub
13. docker image push mantogin/notifications-app:1.0.0 <- размещение образа на dockerhub
14. kubectl apply -f .\kuber\ -n mantogin-hw7
15. cd ..\order-app\
16. docker build --platform linux/amd64 -t mantogin/order-app:1.0.0 . <- сборка для заливки на dockerhub
17. docker image push mantogin/order-app:1.0.0 <- размещение образа на dockerhub
18. https://hub.docker.com/repository/docker/mantogin/mantogin-auth-app/general <- Проверить, что докер залился на докерхаб
19. 7. # kubectl delete -f ./kuber/deployment.yaml <- Удалить развертывание образа
20. # minikube image rm mantogin/order-app:1.0.0 <- Выполнить команду если в выводе предыдущей комадны в списке образов был образ minikube image rm mantogin/order-app:1.0.0
21. kubectl apply -f .\kuber\ -n mantogin-hw7
22. cd ..
23. kubectl apply -f .\kuber\ -n mantogin-hw7
24. minikube tunnel <- Запустить в отдельном окне
25. newman run hw7-postman-collection.json
