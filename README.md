# KotlinCheckout

Prueba Backend
La prueba consiste en un Ecommerce sencillo, su trabajo consiste en realizar el módulo
correspondiente al carrito de compras, este módulo cuenta con los siguientes requerimientos
1. Un producto debe contener nombre, sku, descripción y precio.
2. Existen dos tipos de productos, el producto simple y el producto con descuento. El
precio del producto con descuento es el ingresado divido por 2
3. Un carrito debe contener la lista de los productos asociada a él, cada producto en el
carrito debe tener su cantidad correspondiente (ej: Pintura 6 unidades, cemento 3
unidades, etc) y un estado, pendiente o completado según sea el caso.
4. Los identificadores de las entidades deben ser un UUID
5. El manejo de los estados debe hacerse con enumerables
Las funcionalidades esperadas son las siguientes:
● Lista de todos los productos
● Agregar, eliminar y modificar productos
● Lista de todos los productos de un carrito
● Agregar, eliminar y modificar productos al carrito
● Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a
completado.
Tecnología requerida
● Spring 2.3.4 Kotlin
Duración máxima de la prueba
● 3 días
Entregables
● Repositorio con el código fuente.
Puntos a revisar
- Buenas prácticas en el código y aplicación de los principios SOLID
- Inyección de dependencias por constructor
- Validación del request en el controlador
