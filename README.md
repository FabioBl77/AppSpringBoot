<h1>Istrizioni della repository</h1>

<p>
Questo sito è stato realizzato utilizzando Spring Boot, configurato con Gradle e il database MySql. L'applicazione impiega una serie di dipendenze per garantire funzionalità complete e ottimali, tra cui gestione dei dati, sicurezza, e interfacce utente. Grazie a queste dipendenze, il progetto è altamente scalabile, e facile da estendere con nuove funzionalità.
</p>
<p>(This site was created using Spring Boot, configured with Gradle. The application employs a range of dependencies to ensure comprehensive and optimal functionality, including data management, security, and user interfaces. Thanks to these dependencies, the project is highly scalable, and easy to extend with new features.)</p>

```shimplementation 'com.fasterxml.jackson.core:jackson-databind:2.17.1' 
implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
runtimeOnly 'com.mysql:mysql-connector-j'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
implementation 'org.springframework.boot:spring-boot-starter-mail'
```

<br><p>Questo sito si pone come una simulazione di un ecommerce dedicato alla vendita di componenti per PC. Offre una piattaforma virtuale dove gli utenti possono esplorare una vasta gamma di prodotti e fare acquisti in modo intuitivo e conveniente.</p>

<p>(This site serves as a simulation of an ecommerce platform dedicated to selling PC components. It provides a virtual platform where users can explore a wide range of products and make purchases in an intuitive and convenient manner.)</p><br>

![homePage](https://github.com/FabioBl77/AppSpringBoot/assets/167991278/7d2d4969-6840-440e-a670-668989ae1ff6)

<br><p>Nella home page, è possibile aggiungere i prodotti al carrello, mentre nel menu "Modifica" è possibile rifornire le quantità disponibili in magazzino. Nel menu "Lista" è invece possibile visualizzare tutti i prodotti disponibili per la vendita.</p>

<p>(On the home page, you can add products to your cart, while in the "Modify" menu, you can restock the quantities available in the warehouse. In the "List" menu, you can view all the products available for sale.)</p><br>

![Screenshot 2024-06-04 145950](https://github.com/FabioBl77/AppSpringBoot/assets/167991278/f7e87312-0908-4e36-8cb6-fd3cacb076a6)



<br><p>Dopo aver selezionato il prodotto e impostato le quantità desiderate, è sufficiente premere il pulsante "Conferma" per procedere.</b>

<p>(After selecting the product and setting the desired quantities, simply press the "Confirm" button to proceed.)</p><br><br>



![carrello](https://github.com/FabioBl77/AppSpringBoot/assets/167991278/0ea109f9-79a7-4b54-b24f-d41e42058932)



<br><p>Dopo aver confermato l'ordine, comparirà un riepilogo dettagliato dell'acquisto e ti verrà richiesto di inserire l'indirizzo email. A quel punto, riceverai un'email di riepilogo dell'ordine.</p>

<p>(After confirming the order, a detailed summary of the purchase will appear, and you will be prompted to enter your email address. At that point, you will receive an email summarizing the order.)</p><br>



![email](https://github.com/FabioBl77/AppSpringBoot/assets/167991278/83c71a1c-0af6-426c-bfe4-53f06ca1f4b9)



<br><p>Inoltre, nella home page avremo accesso a una pagina che fornisce un riepilogo di tutte le vendite, rappresentate attraverso grafici dettagliati.</P>

<p>(Additionally, on the home page, we will have access to a page that provides a summary of all sales, represented through detailed graphs.)</p><br>



![grafici](https://github.com/FabioBl77/AppSpringBoot/assets/167991278/8daf8bf4-0eef-4fc0-afa0-5cf722f04186)




