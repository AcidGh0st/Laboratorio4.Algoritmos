package controller;

import domain.Customer;
import domain.ListException;
import domain.Node;
import domain.SinglyLinkedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class CustomerController
{
    @javafx.fxml.FXML
    private TableView tableView;
    @javafx.fxml.FXML
    private TableColumn<Customer, Integer> idTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> emailTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> nameTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Customer, Integer> ageTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Customer, String> phoneTableColumn;
    @javafx.fxml.FXML
    private BorderPane bp;
    private SinglyLinkedList customerList;
    Alert alert;

    @javafx.fxml.FXML
    public void initialize() {
        this.alert = util.UtilityFX.alert("Customers Management", "");
        //cargamos la lista global
        this.customerList = util.Utility.getCustomerList();

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        try{
            if(customerList!=null && !customerList.isEmpty()) {
                for (int i = 1; i <= customerList.size(); i++) {
                    tableView.getItems().add(customerList.getNode(i).data);
                }
            }
        }catch(ListException ex){
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void addFirstOnAction(ActionEvent actionEvent) {

    }


    @javafx.fxml.FXML
    public void clearOnAction(ActionEvent actionEvent) {
        //Elimina todos los elementos de la lista
        customerList.clear();

        //Limpia la tabla
        tableView.getItems().clear();
    }



    @javafx.fxml.FXML
    public void removeOnAction(ActionEvent actionEvent) {
        //Obtiene el índice seleccionado en la tabla
        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();

        //Verifica si se seleccionó algún elemento
        if (selectedIndex >= 0) {
            //Elimina el cliente de la lista
            try {
                customerList.remove(selectedIndex + 1); // Se suma 1 porque la lista está basada en índices comenzando desde 1
            } catch (ListException e) {
                //Maneja la excepción si es lanzada
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return; //Sale del método
            }

            //Elimina el cliente de la tabla
            tableView.getItems().remove(selectedIndex);
        } else {
            //Muestra un mensaje si no se seleccionó ningún elemento
            alert.setContentText("Please select a customer to remove.");
            alert.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.UtilityFX.loadPage("ucr.lab.HelloApplication", "addCustomer.fxml", bp);
    }

    @javafx.fxml.FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
        try {
            //Verifica si la lista de clientes no está vacía
            if (!customerList.isEmpty()) {
                //Obtiene el primer cliente de la lista
                Customer firstCustomer = (Customer) customerList.getFirst();

                //Muestra una alerta con la información del primer cliente
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("First Customer:\n" +
                        "ID: " + firstCustomer.getId() + "\n" +
                        "Name: " + firstCustomer.getName() + "\n" +
                        "Age: " + firstCustomer.getAge() + "\n" +
                        "Phone: " + firstCustomer.getPhoneNumber() + "\n" +
                        "Email: " + firstCustomer.getEmail());
                alert.showAndWait();
            } else {
                //Muestra un mensaje de alerta si la lista de clientes está vacía
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The customer list is empty.");
                alert.showAndWait();
            }
        } catch (ListException e) {
            //Manejr la excepción si ocurre al obtener el primer cliente
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            //Verifica si la lista de clientes no está vacía
            if (!customerList.isEmpty()) {
                //Elimina el primer cliente de la lista
                Customer removedCustomer = (Customer) customerList.removeFirst();

                //Elimina el primer elemento de la tabla
                tableView.getItems().remove(0);

                //Muestra una alerta informando que el cliente fue eliminado
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("The first customer was removed from the list.");
                alert.showAndWait();
            } else {
                //Muestra un mensaje de alerta si la lista de clientes está vacía
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The customer list is empty.");
                alert.showAndWait();
            }
        } catch (ListException e) {
            //Maneja la excepción si ocurre al eliminar el cliente
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void getLastOnAction(ActionEvent actionEvent) {
        try {
            //Verifica si la lista de clientes no está vacía
            if (!customerList.isEmpty()) {
                //Obtiene el último cliente de la lista
                Customer lastCustomer = (Customer) customerList.getLast();

                //Muestra una alerta con la información del último cliente
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Last Customer:\n" +
                        "ID: " + lastCustomer.getId() + "\n" +
                        "Name: " + lastCustomer.getName() + "\n" +
                        "Age: " + lastCustomer.getAge() + "\n" +
                        "Phone: " + lastCustomer.getPhoneNumber() + "\n" +
                        "Email: " + lastCustomer.getEmail());
                alert.showAndWait();
            } else {
                //Muestra un mensaje de alerta si la lista de clientes está vacía
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("The customer list is empty.");
                alert.showAndWait();
            }
        } catch (ListException e) {
            //Maneja la excepción si ocurre al obtener el último cliente
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }
    }


    @javafx.fxml.FXML
    public void containsOnAction(ActionEvent actionEvent) {
        //Obtiene el valor a buscar (en este caso, el ID del cliente)
        String searchValue = ""; // nicializamos con un valor vacío

        //Pedir al usuario que ingrese el ID a buscar utilizando un cuadro de diálogo de JavaFX
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Search Customer");
        dialog.setHeaderText("Enter the ID to search:");
        dialog.setContentText("ID:");

        //muestra el cuadro de diálogo y obtener el resultado
        String result = dialog.showAndWait().orElse(null);

        //Verifica si el resultado es null (el usuario canceló)
        if (result == null) {
            //
            return;
        }

        //Asigna el valor ingresado por el usuario
        searchValue = result;

        //Busca el cliente por su ID en la lista de clientes
        boolean found = false; 
        try {
            for (int i = 1; i <= customerList.size(); i++) {
                Node currentNode = customerList.getNode(i);
                Customer customer = (Customer) currentNode.getData();

                //Busca por ID
                if (Integer.toString(customer.getId()).equals(searchValue)) {
                    found = true;

                    //Muestra la información del cliente encontrado
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Customer Found:");
                    alert.setContentText("ID: " + customer.getId() + "\n" +
                            "Name: " + customer.getName() + "\n" +
                            "Age: " + customer.getAge() + "\n" +
                            "Phone: " + customer.getPhoneNumber() + "\n" +
                            "Email: " + customer.getEmail());
                    alert.showAndWait();

                    break; //Salir del ciclo si se encuentra el cliente
                }
            }
        } catch (ListException e) {
            //Maneja la excepción si ocurre al obtener un nodo o al obtener el tamaño de la lista
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

        //Muestra un mensaje si no se encuentra el cliente con el ID buscado
        if (!found) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Customer with ID '" + searchValue + "' not found.");
            alert.showAndWait();
        }
    }



    @javafx.fxml.FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = customerList.size(); //Obtiene el tamaño de la lista de clientes
            System.out.println("Size of the customer list: " + size); //Imprime el tamaño por consola
        } catch (ListException e) {
            //Maneja la excepción si es lanzada
            e.printStackTrace();
        }
    }
    void customerTest() {
        try {
            SinglyLinkedList customerList = new SinglyLinkedList();

            customerList.add(new Customer(1, "Emma", 21, "111111111", "emma@gmail.com"));
            customerList.add(new Customer(2, "Mateo", 22, "222222222", "mateo@gmail.com) "));
            customerList.add(new Customer(3, "Isabella", 20, "333333333", "isabella@gmail.com"));
            customerList.add(new Customer(5, "Santiago", 21, "555555555", "santi@gmail.com"));
            customerList.add(new Customer(6, "Fabiana", 18, "666666666", "fabi@gmail.com"));
            customerList.add(new Customer(7, "María", 23, "777777777", "maria@gmail.com"));
            customerList.add(new Customer(8, "Carlos", 25, "888888888", "carlos@gmail.com"));
            customerList.add(new Customer(9, "Camila", 19, "999999999", "camila@gmail.com"));
            customerList.add(new Customer(10, "Luka", 30, "101010101", "luka@gmail.com"));
            customerList.add(new Customer(4, "Victoria", 27, "444444444", "victoria@gmail.com"));


            System.out.println(customerList); //Imprimir por consola la lista original


            System.out.println(" ");
            System.out.println("¿Camila Id=9 is a Customer? " + customerList.contains(new Customer(9, "Camila")));
            System.out.println("¿Isabella Id=3 is a Customer?  " + customerList.contains(new Customer(3, "Isabella")));
            System.out.println("¿Carlos Id=5 is a Customer? " + customerList.contains(new Customer(5, "Carlos")));
            System.out.println("¿Carlos Id=8 is a Customer?  " + customerList.contains(new Customer(8, "Carlos")));


            //EJERCICIO 3:recorrer la lista y mostrar los elementos almacenados en cada nodo
            System.out.println(" ");
            System.out.println("III. RECORRER LA LISTA Y MOSTRAR LOS ELEMENTOS ALMACENADOS EN CADA NODO");
            System.out.println(" ");
            for (int i = 1; i <= customerList.size(); i++) { //Ciclo que recorre la lista, siempre y cuando la variable i sea menor al tamaño que posee la lista

                try { //Excepcion
                    Node currentNode = customerList.getNode(i); //Se declara una variable de tipo Node la cual obtiene el nodo en la posicion i de la lista
                    Customer customer = (Customer) currentNode.getData(); //Se declara una variable customer de tipo Customer la cual va a obtener los datos o elementos que posee cada Node en la posicion i
                    System.out.println("Customer at position " + i + ": " + customer.toString()); //Se manda un mensaje por consola que muestra cada elemento que tiene el Node en la forma en la que se indico en ele metodo toString de la clase Customer

                } catch (ListException e) {
                    System.out.println(e.getMessage()); //Muestra mensaje si ocurre una excepcion
                }
            }

            /*EJERCICIO 4
            Determinar el índice de ciertos elemento
            En este caso se analizan los id de los customers por medio del metodo indexOf de la clase Single, esto con el fin de analizar si ese id se encuentra almacenado en la lista
            Los demas atributos del constructor van vacios, ya que solo interesa analizar el id del objeto, en caso de que se quiere analizar todo el arreglo, entonces se debe de completar todos los espacios del objeto
            Se llama al metodo indexOf, el cual realiza una comprobacion de que el objeto este dentro de la lista y en cual indice es en el que se encuentra

            En caso de que si se encuentre el id, debe de retornar el indice en el que esta posicionado ese elemento
             */

            System.out.println(" ");
            System.out.println("IV. DETERMINAR LOS INDICES DE CIERTOS ELEMENTOS DE LA LISTA");
            System.out.println("Customer Santiago, Id=5 exists at position: " + customerList.indexOf(new Customer(5, "Santiago", 0, "", "")));
            System.out.println("Customer Fabiana, Id=6 exists at position: " + customerList.indexOf(new Customer(6, "Fabiana", 0, "", "")));
            System.out.println("Customer Luka, Id=10 exists at position: " + customerList.indexOf(new Customer(10, "Luka", 0, "", "")));


            /*
            EJERCICIO 5
            Ordenar la lista de clientes por nombre

            En este caso, se creo un segundo metodo sort, llamado sortName(), debido a que la burbuja creada en el metodo sort() no funcionaba para ordenar los nombres sino que ordenaba los id de los Customers
            Es por esta razon que se decidio crear otra segunda burbuja la cual se encarga de ordenar la lista conforme al nombre de cada objeto
             */
            System.out.println(" ");
            System.out.println("V. ORDENAR LA LISTA DE CUSTOMERS POR NOMBRE");
            System.out.println(" ");
            customerList.sortName(); //Llamar al metodo que ordena la lista conforme al nombre
            System.out.println(customerList); // Mostrar el contenido de la lista ordenada por nombres


            //EJERCICIO 6. REMOVER LOS ID PARES Y ORDENAR LOS ID IMPARES EN UNA NUEVA LISTA

            System.out.println("VI. REMOVER LOS ID PARES Y ORDENAR LOS ID IMPARES EN UNA NUEVA LISTA");

            //Se instancia una segunda lista, la cual va a estar vacia, esto con el fin de que la lista original no se vea alterada al suprimir elementos de la lista
            SinglyLinkedList secondList= new SinglyLinkedList();

            try {
                int index = 1; //Se declara una variable entera con valor inicial 1, es un contador

                while (index <= customerList.size()) { //Se crea un ciclo que verifica si la condicion se cumple, que es cuando el index sea menor o igual al tamaño de la lista original, entonces se sigue el ciclo

                    Node currentNode = customerList.getNode(index);//Declarar una variable de tipo Node que obtenga los nodos de la lista original con respecto al indice
                    Customer customer = (Customer) currentNode.getData(); //Declarar una variable de tipo Customer en donde se almacenan los datos que se extraen del nodo

                    if (customer.getId() % 2 != 0) { //Condicion en el que analiza si el dato obtenido (id) al dividirlo por 2, el producto sea cero, es decir, verifica si el id procesado es un numero par o impar.
                        secondList.add(customer); //En caso de que sea par (que el resultado de la division sea diferente de cero, se debe almacenar ese objeto en la nueva lista
                    }

                    index++; // Incrementador para seguir analizando los demas nodos de la lista


                }
            } catch (ListException e) {
                System.out.println(e.getMessage());
            }

            // Luego de haber almacenado los id impares en la nueva lista, se ordena con el metodo sort(); implementado en la clase Single, esto para ordenar los elementos segun el id
            try {
                secondList.sort();
            } catch (ListException e) {
                System.out.println(e.getMessage());
            }

            // Mostrar el contenido de la lista por consola
            System.out.println(secondList);


            //EJERCICIO 7. SEPARAR LOS ELEMENTOS POR RANGO DE EDAD
            //Rango de edad
            System.out.println(" ");
            System.out.println("VII.SEPARAR LOS ELEMENTOS POR RANGO DE EDAD");
            System.out.println(" ");
            ListAgeRange(customerList, 0, 20); //Se pasa como parametro los rangos de edad y se llama a la lista original para dividir los objetos comforme a su edad
            ListAgeRange(customerList, 20, 23);
            ListAgeRange(customerList, 25, 30);

        }catch (ListException le){

        }


    }

    void ListAgeRange(SinglyLinkedList customerList, int minimumRange, int maximumRange) throws ListException {

        System.out.println("Customers with age range between " + minimumRange + " and " + maximumRange + " years:");

        for (int i = 1; i <= customerList.size(); i++) { //Ciclo para recorrer el tamaño de la lista hasta recorrer todo el tamaño de la lista

            Node currentNode = customerList.getNode(i); //Se declara una variable de tipo Node la cual obtiene el nodo en la posicion i de la lista
            Customer customer = (Customer)currentNode.getData(); //Declarar una variable de tipo Customer en donde se almacenan los datos que se extraen del nodo

            if (customer.getAge() >= minimumRange && customer.getAge() <= maximumRange) { //Condicion que verifica si se cumple que la edad que posee cada customer, este entre el intervalo que se esta pasando como parametro en el metodo, en caso de que no sea asi, entonces ese elemento no formara parte de ese rango de edades
                System.out.println("Customer: " + customer.getId() + " " + customer.getName() + " " + customer.getAge());
            }
        }
    }

}