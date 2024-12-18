data class Product(val name: String, val price: Double)
data class Order(val products: List<Product>, val isDelivered: Boolean)
data class Customer(val name: String, val orders: List<Order>)
data class Shop(val customers: List<Customer>)

fun Shop.getCustomerWithMaxOrders(): Customer? = customers.maxByOrNull { it.orders.size }

// Return the most expensive product that has been ordered by the given customer
fun getMostExpensiveProductBy(customer: Customer): Product? =
  customer.getOrderedProducts().maxByOrNull {it.price}

fun Customer.getOrderedProducts(): List<Product> =
  orders.flatMap { it.products }

// Return all products that were ordered by at least one customer
fun Shop.getOrderedProducts(): Set<Product> =
  customers.flatMap {it.getOrderedProducts()}.toSet()


fun main() {
  val order1 = Order(listOf(Product("Laptop", 1000.0)), isDelivered = true)
  val order2 = Order(listOf(Product("Smartphone", 500.0)), isDelivered = true)
  val order3 = Order(listOf(Product("Tablet", 300.0)), isDelivered = false)

  val customer1 = Customer("Alice", listOf(order1, order2))
  val customer2 = Customer("Bob", listOf(order2, order3))
  val customer3 = Customer("Charlie", listOf(order1, order2, order3))

  val shop = Shop(listOf(customer1, customer2, customer3))

  val customerWithMaxOrders = shop.getCustomerWithMaxOrders()
  println(customerWithMaxOrders) // Should print: Customer(name=Charlie, orders=[Order(...), Order(...), Order(...)])

  val ordersAtLeastByOneCustomer = shop.getOrderedProducts()
  println(ordersAtLeastByOneCustomer) // Should print: {Product(name=Laptop, price=1000.0), Product(name=Smartphone, price=500.0), Product(name=Tablet, price=300.0)}

  val mostExpensiveProductByCharlie = getMostExpensiveProductBy(customer3)
  println(mostExpensiveProductByCharlie) // Should print: Product(name=Laptop, price=1000.0)
}

