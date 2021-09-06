# EchoBot
Vk Bot, который при упоминании его цитирует присланный текст

## Конфигурация

### ngrok
1. Скачайте и установите [ngrok](https://ngrok.com/)
2. Зарегистрируйтесь на [сайте](https://ngrok.com/) и получите AUTH_TOKEN
3. В ngrok выполните команды:

     `ngrok authtoken <AUTH_TOKEN>`
  
     `ngrok http <PORT>`. PORT - например, 8080
### VK
1. Создайте сообщество ВКонтакте
2. Перейдите на страницу сообщества, выберите вкладку **Управление**,  **Работа с API**
3. Во вкладке **Ключи доступа** нажмите _Создать ключ_, разрешите управление сообщениями. Укажите полученный токен в application.properties в vk.token.
4. Перейдите на вкладку CallBack API. Укажите версию API 5.131, в адресе- `<HTTP-адрес на ngroc>/callback`
5. В application.properties в vk.group.id укажите "group_id" из настроек сервера
6. В application.properties в vk.secretKey придумайте и укажите свой секретный ключ и добавьте его VK
7. В application.properties в vk.confirmationCode укажите "Строку, которую должен вернуть сервер" из VK
8. В application.properties измените vk.confirm.url на /collback, vk.callback.url на что-нибудь другое (например, /callback1)
9. Перейдите VK на вкладку **Типы событий** и включите уведомления о новых входящих сообщениях
10. Запутите приложение, чтобы подтвердить CallBack сервер для ВКонтакте

11. В application.properties измените vk.callback.url на /callback, vk.confirm.url на что-нибудь другое (например, /confirm) 
