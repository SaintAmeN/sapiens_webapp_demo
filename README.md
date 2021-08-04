1. budowa bazy 
2. zdefiniowanie use-case:
    a. do bazy
    b. do serwisu
    c*. stworzyć zapytania (SQL) do bazy które pobierają niezbędne przez Was treści
3. Podstawowa budowa projektu - konfiguracja bazy, brakujące dependencies, tworzymy wszystkie modele, 
   dodajemy wszystkie repository (nawet jeśli bez metod), (serwisy też) - skupiamy się na `getAll` oraz `add`.
4. podstawowe operacje (CRUD) stworzyć w postaci Resource (REST)
5. na koniec interfejs graficzny (frontend i servlety)

// JMS
// wymiana  -> do wszystkich                -> topic
// wymiana  -> do pierwszego który obsłuży  -> queue