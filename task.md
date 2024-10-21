# Оглавление

***

- [Задание 2. Singleton](#задание-2-singleton)
- [Задание 3. Prototype](#задание-3-prototype)
- [Задание 4. Static Factory Method](#задание-4-static-factory-method)
- [Задание 5. Builder](#задание-5-builder)
- [Задание 6. Factory Method](#задание-6-factory-method)
- [Задание 7. Absract Factory](#задание-7-abstract-factory)
- [Задание 8. Adapter](#задание-8-adapter)
- [Задание 9. Bridge](#задание-9-bridge)
- [Задание 10. Composite](#задание-10-composite)

***

## Задание 2. Singleton

### Описание

В этом разделе описан класс [`NoteService`](./src/main/java/ru/patterns/services/NoteService.java), который реализует паттерн проектирования Singleton. Singleton — это паттерн, который ограничивает создание объекта класса одним экземпляром и предоставляет глобальную точку доступа к этому экземпляру.

### Причины выбора Singleton для класса `NoteService`

1. **Глобальная точка доступа**: Паттерн Singleton обеспечивает наличие единственного экземпляра `NoteService` в приложении. Это позволяет централизованно управлять всеми операциями, связанными с заметками, через один объект, что упрощает управление состоянием и логикой.

2. **Контроль над созданием экземпляра**: Поскольку `NoteService` содержит логику для работы с данными, важно, чтобы существовал только один экземпляр этого класса, который управлял бы доступом к репозиторию заметок. Использование Singleton предотвращает создание нескольких экземпляров, что может привести к неконсистентности данных или избыточному потреблению ресурсов.

3. **Ленивое создание**: Экземпляр `NoteService` создается лениво при первом обращении через метод `getInstance()`. Это обеспечивает экономию ресурсов, так как объект создается только тогда, когда он действительно нужен.

### Признаки реализации Singleton в классе `NoteService`

- **Приватный конструктор**: Конструктор класса `NoteService` объявлен как `private`, что предотвращает создание экземпляров класса извне. Это обязательное требование для паттерна Singleton, чтобы контролировать создание объектов.

- **Статическое поле для хранения экземпляра**: В классе определено статическое поле `instance`, которое хранит единственный экземпляр `NoteService`. Это поле используется для проверки, существует ли уже экземпляр класса, и для его создания при необходимости.

- **Статический метод для доступа к экземпляру**: Метод `getInstance()` предоставляет глобальную точку доступа к единственному экземпляру класса. Он проверяет, был ли уже создан экземпляр, и создает его при первом вызове.

Пример кода класса `NoteService`:

```java
/**
 * Паттерн Singleton.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private static NoteService instance;

    private NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public static NoteService getInstance(NoteRepository noteRepository) {
        if (instance == null) {
            instance = new NoteService(noteRepository);
        }
        return instance;
    }

    /**
     * Получает список всех заметок.
     *
     * @return Список всех заметок.
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Добавляет новую заметку в базу данных.
     *
     * @param noteDto DTO заметки, которую нужно добавить.
     * @return Сохраненная заметка.
     */
    public Note addNote(NoteDTO noteDto) {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        return noteRepository.save(note);
    }

    /**
     * Получает заметку по ее уникальному идентификатору.
     *
     * @param id Уникальный идентификатор заметки.
     * @return Optional, содержащий заметку, если она найдена, иначе пустой
     *         Optional.
     */
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    /**
     * Удаляет заметку из базы данных по ее уникальному идентификатору.
     *
     * @param id Уникальный идентификатор заметки, которую нужно удалить.
     */
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
```

***

## Задание 3. Prototype

### Описание

В этом разделе описан класс [`Note`](./src/main/java/ru/patterns/models/Note.java), который реализует паттерн проектирования Prototype. Prototype — это паттерн, который позволяет создавать новые объекты путем копирования уже существующих. Вместо создания нового экземпляра через конструктор, мы используем метод копирования, чтобы создать дубликат объекта.

### Причины выбора Prototype для класса `Note`

1. **Гибкость создания объектов**: Паттерн Prototype упрощает процесс создания дубликатов объектов. В случае класса `Note`, это важно, когда требуется скопировать существующую заметку и создать ее клон с аналогичными свойствами.

2. **Изоляция логики копирования**: Логика копирования инкапсулирована внутри объекта `Note`, что позволяет избежать дублирования кода и повышает гибкость программы. Всякий раз, когда требуется создать копию заметки, паттерн гарантирует, что все необходимые поля будут скопированы корректно.

3. **Упрощение дублирования объектов**: Использование метода `copy()` позволяет избежать сложностей, связанных с созданием новых объектов через конструктор, особенно если объект имеет сложную структуру или большое количество полей.

### Признаки реализации Prototype в классе `Note`

- **Реализация интерфейса Prototype**: Класс `Note` реализует интерфейс `Prototype`, который содержит метод `copy()`. Это позволяет классу явно определить, каким образом будет создана копия объекта.

- **Метод `copy()` для создания дубликатов**: Метод `copy()` создает новый объект `Note`, копируя значения полей `title` и `content` оригинальной заметки. Это ключевая часть паттерна Prototype, которая позволяет создавать дубликаты объектов.

### Пример кода класса `Note`:

```java
/**
 * Паттерн Prototype.
 */
@Entity
@Table(name = "notes")
public class Note implements Prototype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String content;
    private LocalDateTime timestamp;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

    public Note() {
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Создает копию объекта Note.
     * 
     * @return Новый объект Note с теми же значениями полей.
     */
    @Override
    public Prototype copy() {
        return new Note(this.title, this.content);
    }
}
```
***

## Задание 4. Static Factory Method

### Описание

В этом разделе описан класс [`NoteUtils`](./src/main/java/ru/patterns/utils/NoteUtils.java), который реализует паттерн проектирования Static Factory Method. Этот паттерн предоставляет статические методы для создания и изменения объектов, что улучшает читаемость и управление кодом.

### Причины выбора Static Factory Method для класса `NoteUtils`

1. **Упрощение создания объектов**: Паттерн Static Factory Method позволяет создавать и изменять объекты через статические методы, что упрощает код и делает его более понятным. Вместо создания объектов через конструктор, мы можем использовать методы с ясными названиями.

2. **Удобство использования**: Статические методы могут быть вызваны без необходимости создания экземпляра класса, что делает их удобными для использования в различных частях приложения.

### Признаки реализации Static Factory Method в классе `NoteUtils`

- **Статические методы для изменения свойств**: В классе определены статические методы `changeTitle`, `changeContent` и `updateNote`, которые изменяют соответствующие свойства объекта `Note`.

- **Приватный конструктор**: Конструктор класса `NoteUtils` объявлен как `private`, что предотвращает создание экземпляров этого класса. Это является стандартной практикой для утилитарных классов, содержащих только статические методы.

### Пример кода класса `NoteUtils`:

```java
/**
 * Паттерн Static Factory Method.
 */
public class NoteUtils {

    private NoteUtils() {
    }

    public static void changeTitle(Note note, String newTitle) {
        note.setTitle(newTitle);
    }

    public static void changeContent(Note note, String newContent) {
        note.setContent(newContent);
    }

    public static void updateNote(Note note, String newTitle, String newContent) {
        note.setTitle(newTitle);
        note.setContent(newContent);
        note.setTimestamp(LocalDateTime.now());
    }
}
```


## Задание 5. Builder

### Описание

В этом разделе описан класс [`SwaggerConfig`](./src/main/java/ru/patterns/config/SwaggerConfig.java), который реализует паттерн проектирования Builder. Паттерн Builder предоставляет способ пошагового создания сложных объектов, позволяя избежать необходимости передавать множество параметров в конструктор.

### Причины выбора Builder для класса `SwaggerConfig`

1. **Упрощение конфигурации**: Использование паттерна Builder в `SwaggerConfig` упрощает процесс настройки групп API для Swagger. Вместо создания экземпляров с помощью длинного списка параметров, вы можете пошагово настраивать объект, что делает код более понятным и поддерживаемым.

2. **Повышение читабельности кода**: Паттерн Builder позволяет явно указывать, какие параметры вы настраиваете для объекта. Это упрощает понимание конфигурации и ее последующее редактирование, особенно если в будущем потребуется добавить новые параметры.


### Признаки реализации Builder в классе `SwaggerConfig`

- **Использование методов для настройки параметров**: В классе `SwaggerConfig` используются методы `group()`, `packagesToScan()`, и `pathsToMatch()` для пошаговой настройки экземпляра `GroupedOpenApi`. Это позволяет четко структурировать код и легко добавлять новые параметры при необходимости.

- **Финальный метод `build()`**: Метод `build()` используется для создания окончательного экземпляра объекта `GroupedOpenApi`. Это явный сигнал о том, что все необходимые параметры были установлены, и объект готов к использованию.

### Пример кода класса `SwaggerConfig`:

```java
/**
 * Паттерн Builder.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi notesApi() {
        return GroupedOpenApi.builder()
                .group("notes-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/notes/**")
                .build();
    }

    @Bean
    public GroupedOpenApi homeApi() {
        return GroupedOpenApi.builder()
                .group("home-api")
                .packagesToScan("ru.patterns.controllers")
                .pathsToMatch("/")
                .build();
    }
}
```


## Задание 6. Factory Method

### Описание

В этом разделе описаны классы и интерфейсы, которые реализуют паттерн проектирования **Factory Method** для создания объектов различных форм в приложении с использованием класса [`Canvas`](./src/main/java/ru/patterns/models/Canvas.java). Паттерн Factory Method предоставляет интерфейс для создания объектов в суперклассе, позволяя подклассам изменять тип создаваемого объекта.

### Причины выбора Factory Method для создания фигур

1. **Отделение создания объектов от их использования**: Паттерн Factory Method позволяет отделить логику создания фигур от их использования. Это упрощает управление созданием объектов и улучшает гибкость кода, поскольку детали создания могут быть изменены, не влияя на остальную часть приложения.

2. **Поддержка расширяемости**: Легко добавлять новые типы фигур, просто создавая новые реализации интерфейса [`ShapeFactory`](./src/main/java/ru/patterns/factory/ShapeFactory.java), не меняя существующий код. Это позволяет расширять функциональность приложения, не нарушая уже работающий функционал.


### Признаки реализации Factory Method в классе

- **Интерфейс [`ShapeFactory`](./src/main/java/ru/patterns/factory/ShapeFactory.java)**  **: Этот интерфейс определяет метод `createShape()`, который должен быть реализован всеми конкретными фабриками. Это позволяет создавать различные формы, не указывая конкретные классы, которые будут созданы.

- **Конкретные фабрики**: Классы [`CircleFactory`](./src/main/java/ru/patterns/factory/CircleFactory.java) и [`RectangleFactory`](./src/main/java/ru/patterns/factory/RectangleFactory.java) реализуют интерфейс [`ShapeFactory`](./src/main/java/ru/patterns/factory/ShapeFactory.java) и предоставляют конкретные реализации метода `createShape()`, создавая соответствующие объекты [`Circle`](./src/main/java/ru/patterns/models/Circle.java] и [`Rectangle`](./src/main/java/ru/patterns/models/Rectangle.java). Эти фабрики принимают параметры, необходимые для создания объектов.

### Пример кода классов Factory Method

#### Интерфейс ShapeFactory

```java
public interface ShapeFactory {
    Shape createShape();
}
```

#### Конкретные фабрики

```java
public class RectangleFactory implements ShapeFactory {

    private double width;
    private double height;

    public RectangleFactory(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Shape createShape() {
        return new Rectangle(width, height);
    }
}

public class CircleFactory implements ShapeFactory {

    private double radius;

    public CircleFactory(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape createShape() {
        return new Circle(radius);
    }
}
```


# Задание 7. Abstract Factory

## Описание

В этом разделе описаны классы и интерфейсы, которые реализуют паттерн проектирования **Abstract Factory** для создания объектов блокнотов и блокнотов различных типов в приложении. Abstract Factory предоставляет интерфейс для создания семейства связанных или зависимых объектов без указания их конкретных классов.

## Причины выбора Abstract Factory

1. **Создание семейства объектов**: Паттерн Abstract Factory идеально подходит, когда нужно создать группу объектов, которые связаны между собой. В нашем случае это тетради и блокноты, каждый из которых может иметь различные реализации (школьные, офисные и дизайнерские).

2. **Изоляция клиентского кода от конкретных реализаций**: Abstract Factory изолирует процесс создания объектов от их использования, что повышает гибкость и упрощает поддерживаемость кода. Пользователи фабрики взаимодействуют с общими интерфейсами, не зная деталей создания объектов.

3. **Легкость расширения**: Добавление новых типов блокнотов или блокнотов возможно без изменения существующего кода. Нужно лишь создать новую реализацию фабрики и объектов, что делает систему гибкой и расширяемой.

## Признаки реализации Abstract Factory

- **Интерфейс `StationeryFactory`**: Этот интерфейс определяет методы для создания объектов `NoteBook` и `NotePad`, предоставляя абстракцию для всех конкретных фабрик.

- **Конкретные фабрики**: Классы `OfficeStationeryFactory` и `SchoolStationeryFactory` реализуют интерфейс `StationeryFactory` и предоставляют конкретные реализации методов для создания офисных и школьных блокнотов и блокнотов соответственно.

- **Интерфейсы для продуктов**: Интерфейсы `NoteBook` и `NotePad` описывают общие методы для всех блокнотов и блокнотов, позволяя реализовать различные типы этих объектов.

- **Конкретные продукты**: Классы `OfficeNotebook`, `SchoolNotebook`, `SimpleNotepad` и `DesignerNotepad` представляют конкретные реализации продуктов.

## Код классов Abstract Factory

### Интерфейс StationeryFactory

```java
public interface StationeryFactory {
    NoteBook createNoteBook();
    NotePad createNotePad();
}
```


### Конкретные фабрики

```java
public class OfficeStationeryFactory implements StationeryFactory {

    @Override
    public NoteBook createNoteBook() {
        return new OfficeNotebook();
    }

    @Override
    public NotePad createNotePad() {
        return new SimpleNotepad();
    }
}

public class SchoolStationeryFactory implements StationeryFactory {

    @Override
    public NoteBook createNoteBook() {
        return new SchoolNotebook();
    }

    @Override
    public NotePad createNotePad() {
        return new DesignerNotepad();
    }
}
```

### Интерфейсы для продуктов

```java
public interface NoteBook {
    void describe();
}

public interface NotePad {
    void describe();
}
```

### Конкретные продукты

```java
public class OfficeNotebook implements NoteBook {

    @Override
    public void describe() {
        System.out.println("This is an office notebook.");
    }
}

public class SchoolNotebook implements NoteBook {

    @Override
    public void describe() {
        System.out.println("This is a school notebook.");
    }
}

public class SimpleNotepad implements NotePad {

    @Override
    public void describe() {
        System.out.println("This is a simple notepad.");
    }
}

public class DesignerNotepad implements NotePad {

    @Override
    public void describe() {
        System.out.println("This is a designer notepad.");
    }
}
```


## Задание 8. Adapter

### Описание

В этом разделе описан класс `TemperatureServiceAdapter`, который реализует паттерн проектирования Adapter. Adapter позволяет несовместимым интерфейсам работать вместе, преобразуя интерфейс одного класса в ожидаемый клиентом интерфейс.

### Причины выбора Adapter для `TemperatureServiceAdapter`

1. **Совместимость интерфейсов**: Adapter обеспечивает совместимость между интерфейсом `TemperatureService` и классом `WeatherApiClient`, позволяя использовать его без изменений.

2. **Скрытие сложности**: Адаптер скрывает сложность работы с внешним API, предоставляя простой интерфейс для получения температуры в городе.

3. **Повторное использование кода**: Адаптер позволяет повторно использовать `WeatherApiClient`, снижая дублирование кода и упрощая разработку.

### Признаки реализации Adapter в `TemperatureServiceAdapter`

- **Имплементация целевого интерфейса**: `TemperatureServiceAdapter` реализует интерфейс `TemperatureService` с методом `getTemperature`.

- **Взаимодействие с адаптируемым объектом**: Метод `getTemperature` вызывает `getCurrentWeatherData` из `WeatherApiClient` и извлекает температуру.

### Реализация паттерна Adapter

```java
// Интерфейс целевого объекта
public interface TemperatureService {
    Double getTemperature(String city);
}

// Интерфейс адаптируемого объекта
public interface WeatherApi {
    Map<String, Object> getCurrentWeatherData(String city);
}

// Конкретная реализация адаптируемого объекта
@Component
public class WeatherApiClient implements WeatherApi {

    private final RestTemplate restTemplate;
    private final String apiKey = "6996b38d22fd438a876113325240910";

    public WeatherApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map getCurrentWeatherData(String city) {
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();
    }
}

// Адаптер
@Service
public class TemperatureServiceAdapter implements TemperatureService {

    private final WeatherApiClient weatherApiClient;

    public TemperatureServiceAdapter(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    @Override
    public Double getTemperature(String city) {
        var current = (Map) weatherApiClient.getCurrentWeatherData(city).get("current");
        return (Double) current.get("temp_c");
    }
}


```

# Задание 9. Bridge

<img src="assets/bridge.png" alt="Мост" width="592">

## Описание

В этом разделе описан класс, который реализует паттерн проектирования Bridge. Паттерн Bridge разделяет абстракцию и реализацию, позволяя изменять их независимо.

## Преимущества использования паттерна Bridge:

- Упрощение системы, избегая создания большого количества подклассов.
- Возможность изменять реализацию и абстракцию независимо, что упрощает расширение системы.
- Улучшение читаемости и поддерживаемости кода.

## Пример реализации паттерна Bridge:

В данном проекте используется паттерн Bridge, где абстракция представлена классом [`Shape`](./src/main/java/ru/patterns/models/Shape.java), а реализация — классами [`Color`](./src/main/java/ru/patterns/interfaces/Color.java) (например, [`RedColor`](./src/main/java/ru/patterns/models/RedColor.java) и [`BlueColor`](./src/main/java/ru/patterns/models/BlueColor.java)).

- **Абстракция**: `Shape` (класс, представляющий геометрическую фигуру, с методом `draw()`, который будет использовать цвет).
- **Реализация**: `Color` (интерфейс, определяющий методы для применения цвета).
- **Конкретные реализации**: `RedColor` и `BlueColor` (классы, реализующие интерфейс `Color`).

Такое разделение позволяет добавлять новые фигуры или цвета, не изменяя существующие классы.



***


## Задание 10. Composite

### Описание

В этом разделе описан класс `CanvasComponent`, который реализует паттерн проектирования **Composite**. Composite — это паттерн, позволяющий создавать древовидные структуры объектов, где каждая ветвь или лист структуры может обрабатываться единообразно. Он объединяет объекты в древовидные структуры для представления иерархий «часть-целое».

### Причины выбора Composite для структуры «Canvas» и «Folders»

1. **Удобная иерархическая структура**:
   Composite позволяет организовать структуру объектов в виде дерева, что подходит для хранения элементов, таких как папки и холсты, в системе, где они могут быть сгруппированы в другие папки или быть листовыми элементами (например, `Canvas`). Каждый элемент в такой структуре рассматривается одинаково — будь то отдельный элемент или целая группа элементов.

2. **Единая обработка компонентов**:
   Composite обеспечивает единый интерфейс для управления как простыми объектами (например, холстами), так и составными объектами (например, папками). Это делает код более гибким и упрощает работу с элементами, позволяя использовать одну и ту же логику как для отдельных объектов, так и для их коллекций.

3. **Гибкость в добавлении и удалении элементов**:
   Composite предоставляет методы для добавления, удаления и получения компонентов в составе других компонентов. Это полезно в тех случаях, когда объекты могут быть объединены в более сложные структуры иерархии, например, когда холсты могут находиться в папках, а папки могут содержать как холсты, так и другие папки.

### Признаки реализации Composite в классе `CanvasComponent`

- **Абстрактный базовый класс или интерфейс**:
  `CanvasComponent` является базовым классом, который определяет общие операции для всех компонентов иерархии, такие как методы `add()`, `remove()` и `getChildren()`. Этот класс представляет общие операции для всех объектов (папок и холстов) в структуре.

- **Компоненты-листья и составные компоненты**:
  В структуре реализованы как компоненты-листья (`Canvas`), так и составные компоненты (`CanvasFolder`), которые могут содержать другие компоненты. Это ключевая часть Composite, где листья (примитивные элементы) и составные элементы обрабатываются единообразно.

- **Методы для управления дочерними элементами**:
  В составных компонентах (например, в `CanvasFolder`) реализованы методы для добавления, удаления и получения дочерних компонентов, что делает возможным создание древовидных структур.

### Реализация паттерна Composite

```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CanvasComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void add(CanvasComponent component);

    public abstract void remove(CanvasComponent component);

    public abstract List<CanvasComponent> getChildren();
}

@Entity
@Table(name = "canvas")
public class Canvas extends CanvasComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "canvas", cascade = CascadeType.ALL)
    private List<Shape> shapes = new ArrayList<>();

    public Canvas() {
    }

    public Canvas(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void add(CanvasComponent component) {
        throw new UnsupportedOperationException("Canvas cannot contain other components.");
    }

    @Override
    public void remove(CanvasComponent component) {
        throw new UnsupportedOperationException("Canvas cannot contain other components.");
    }

    @Override
    public List<CanvasComponent> getChildren() {
        return Collections.emptyList();
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
        shape.setCanvas(this);
    }
}

@Entity
public class CanvasFolder extends CanvasComponent {

    @OneToMany(cascade = CascadeType.ALL)
    private final List<CanvasComponent> children = new ArrayList<>();

    public CanvasFolder() {
    }

    public CanvasFolder(String name) {
        this.setName(name);
    }

    @Override
    public void add(CanvasComponent component) {
        children.add(component);
    }

    @Override
    public void remove(CanvasComponent component) {
        children.remove(component);
    }

    @Override
    public List<CanvasComponent> getChildren() {
        return children;
    }
}
```
