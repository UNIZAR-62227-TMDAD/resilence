# Resiliencia

Este proyecto contiene un ejemplo de

- servidor de configuración centralizado (`config-server`)
- servidor de descubrimiento de servicios (`eureka-server`)
- servicio que usa los dos anteriores (`config-client`)
- cliente que invoca a un servicio usando el servicio de configuración, el deservicio de descubrimiento y que además tiene un circuit breaker como refuerzo (`eureka-client`)