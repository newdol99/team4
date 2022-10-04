a. secret.yaml 파일 생성


```yaml
apiVersion: v1
kind: Secret
metadata:
  name: team4-pass
type: Opaque
data:
  password: dGVhbTRmaWdodGluZw==
```

b. 해당 secret을 사용할 mysql pod용 yaml 파일 생성

   secretkey는 team4-pass


```yaml
apiVersion: v1
kind: Pod
metadata:
  name: mysql
  labels:
    name: lbl-k8s-mysql
spec:
  containers:
  - name: mysql
    image: mysql:latest
    env:
    - name: MYSQL_ROOT_PASSWORD
      valueFrom:
        secretKeyRef:
          name: team4-pass
          key: password
    ports:
    - name: mysql
      containerPort: 3306
      protocol: TCP
    volumeMounts:
    - name: k8s-mysql-storage
      mountPath: /var/lib/mysql
  volumes:
  - name: k8s-mysql-storage
    emptyDir: {}
```



c. 현재 secrets없음 확인

![](images/secret3.jpg)



d. secret.yaml 적용

   team4-pass 가 생성됨을 확인

![](images/secret4.jpg)




e. mysql 파트 생성(secret키 사용할 app)

![](images/secret5.jpg)



f. pod 생성됨을 확인

![](images/secret6.jpg)



g. mysql pod에 들어가서 해당 secret 키 내용 확인 

![](images/secret7.jpg)

```
team4fighting이라는 secret으로 된 password 확인 가능
```