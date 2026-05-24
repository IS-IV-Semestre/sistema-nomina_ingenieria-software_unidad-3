# Sistema de Nómina — Ingeniería de Software · Unidad 3

Sistema de gestión de nómina desarrollado en Java como proyecto académico para la asignatura de Ingeniería de Software (Cuarto Semestre — UdC). Implementa los principios de **Arquitectura Hexagonal (Ports & Adapters)** y **Domain-Driven Design (DDD)**.

---

## Arquitectura

El proyecto sigue el patrón de **Arquitectura Hexagonal**, organizando el código en tres capas bien definidas:

```
src/main/java/com/udc/
├── domain/                         # Núcleo del negocio (sin dependencias externas)
│   ├── models/empleado/            # Entidades del dominio
│   ├── valueobjects/empleado/      # Value Objects con validación
│   ├── enums/empleado/             # Enumeraciones del dominio
│   └── exceptions/empleado/        # Excepciones de dominio
├── application/                    # Casos de uso
│   ├── port/in/                    # Puertos de entrada (interfaces de use case)
│   ├── port/out/                   # Puertos de salida (interfaces de repositorio)
│   ├── service/                    # Implementaciones de los casos de uso
│   │   └── dto/                    # Commands y Queries
│   └── empleado/                   # Servicios fachada para la capa de consola
├── adapters/console/               # Adaptadores de consola (UI)
├── infrastructure/
│   ├── entrypoint/desktop/cli/     # Menú principal y utilidades de consola
│   └── persistence/inmemory/       # Repositorio en memoria
└── fixtures/                       # Implementaciones en memoria de los puertos
```

### Flujo de datos

```
Usuario → ConsoleAdapter → UseCase (port/in) → Service → Repository (port/out) → InMemoryStore
```

---

## Tipos de empleado

| Tipo | Clase | Salario Bruto | Beneficios | Deducción |
|---|---|---|---|---|
| **Asalariado** | `EmpleadoAsalariado` | Salario mensual fijo | Bono 10% si antigüedad > 5 años + $1.000.000 alimentación | 4% Seguro Social |
| **Por Horas** | `EmpleadoPorHoras` | Horas × tarifa (horas extra a 1.5×) | Fondo ahorro 2% si antigüedad > 1 año y acepta | 4% Seguro Social |
| **Por Comisión** | `EmpleadoPorComision` | Salario base + (ventas × comisión) | Bono 3% ventas si superan $20.000.000 + $1.000.000 alimentación | 4% Seguro Social |
| **Temporal** | `EmpleadoTemporal` | Salario mensual fijo | Sin beneficios | 4% Seguro Social |

---

## Requisitos

- Java 21
- Maven 3.8+

---

## Cómo ejecutar

### Desde la terminal

```bash
# Compilar
mvn compile

# Ejecutar
mvn exec:java -Dexec.mainClass="com.udc.Main"

# O generar el JAR y ejecutar
mvn package -DskipTests
java -cp target/sistema_nomina-1.0-SNAPSHOT.jar com.udc.Main
```

### Desde un IDE

Abrir y ejecutar directamente la clase `Main.java`.

---

## Menú principal

Al iniciar la aplicación se presenta el siguiente menú:

```
BIENVENID@ AL MODULO DE EMPLEADO
1. Listar todos los empleados
2. Registrar un nuevo empleado
3. Actualizar un empleado
4. Eliminar un empleado
5. Salir
```

Al registrar (opción 2) se selecciona el tipo de empleado y se ingresa los datos requeridos. El sistema genera un ID único (UUID) por cada empleado creado.

> Los datos se almacenan en memoria — se pierden al cerrar la aplicación.

---

## Puertos definidos

### Entrada (Input Ports — `application/port/in/`)

| Interface | Operación |
|---|---|
| `CreateEmpleadoAsalariadoUseCase` | Registrar empleado asalariado |
| `CreateEmpleadoPorHorasUseCase` | Registrar empleado por horas |
| `CreateEmpleadoPorComisionUseCase` | Registrar empleado por comisión |
| `CreateEmpleadoTemporalUseCase` | Registrar empleado temporal |
| `UpdateEmpleadoAsalariadoUseCase` | Actualizar empleado asalariado |
| `UpdateEmpleadoPorHorasUseCase` | Actualizar empleado por horas |
| `UpdateEmpleadoPorComisionUseCase` | Actualizar empleado por comisión |
| `UpdateEmpleadoTemporalUseCase` | Actualizar empleado temporal |
| `GetAllEmpleadosUseCase` | Listar todos los empleados |
| `GetEmpleadoByIdUseCase` | Buscar empleado por ID |
| `DeleteEmpleadoUseCase` | Eliminar empleado |

### Salida (Output Ports — `application/port/out/`)

| Interface | Operación |
|---|---|
| `SaveEmpleado*Port` | Persistir empleado (uno por tipo) |
| `UpdateEmpleado*Port` | Actualizar empleado (uno por tipo) |
| `GetAllEmpleadosPort` | Recuperar todos los empleados |
| `GetEmpleadoByIdPort` | Recuperar empleado por ID |
| `DeleteEmpleadoPort` | Eliminar empleado |

---

## Principios aplicados

- **S**ingle Responsibility — cada clase tiene una única razón de cambio
- **O**pen/Closed — `Empleado` es abstracto; se extiende sin modificarse
- **L**iskov Substitution — todos los subtipos reemplazan a `Empleado`
- **I**nterface Segregation — puertos específicos por operación y tipo
- **D**ependency Inversion — los servicios dependen de interfaces, no de implementaciones concretas
