# Оглавление

- [Задание 2. Singleton](#задание-2-singleton)
- [Задание 3. Prototype](#задание-3-prototype)
- [Задание 4. Static Factory Method](#задание-4-static-factory-method)

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