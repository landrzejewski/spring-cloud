### Przygotować:
* Stworzyć usługę umożliwiającą wyszukiwanie połączeń kolejowych (bez logiki tylko przykładowy endpoint rest)
* Wykorzystać poznane elementy Spring Cloud (Config Server, Service Discovery, Gateway)

---

**Wymagania funkcjonalne dla systemu sprzedaży biletów pociągów**

1. **Interfejs użytkownika**
    - Intuicyjny i responsywny interfejs użytkownika dostosowany do różnych rozmiarów ekranów (komputery, tablety, smartfony).

2. **Wyszukiwanie połączeń**
    - Możliwość wyszukiwania połączeń według:
      a) Miejsca początkowego
      b) Miejsca docelowego
      c) Daty i godziny wyjazdu
      d) Opcjonalnie: klasy pociągu (np. ekonomiczna, biznes)
    - Prezentacja dostępnych połączeń wraz z informacją o:
      a) Godzinie odjazdu i przyjazdu
      b) Całkowitym czasie podróży
      c) Liczbie przesiadek
      d) Dostępności miejsc
      e) Cenie biletu
    - Filtracja wyników wyszukiwania według różnych kryteriów (np. najkrótszy czas podróży, najniższa cena).

3. **Kupno biletów**
    - Możliwość wyboru konkretnej trasy z dostępnych połączeń.
    - Wybór klasy podróży oraz liczby biletów.
    - Możliwość rezerwacji miejsca siedzącego, jeśli opcja jest dostępna.
    - Proces płatności:
      a) Wprowadzanie danych płatniczych (karta płatnicza, przelew, portfele elektroniczne itp.)
      b) Potwierdzenie zakupu
      c) Wysłanie biletu w formie elektronicznej na podany adres e-mail oraz opcja pobrania jako PDF.
    - Historia zakupów dostępna dla zarejestrowanych użytkowników.

4. **Informacje o opóźnieniach**
    - System automatycznie pobiera i aktualizuje informacje o ewentualnych opóźnieniach dla konkretnych połączeń.
    - Użytkownicy, którzy zakupili bilety na dane połączenie, otrzymują powiadomienia o ewentualnych opóźnieniach.
    - Możliwość sprawdzenia opóźnień w czasie rzeczywistym w panelu użytkownika oraz na stronie głównej.

5. **Rejestracja i logowanie**
    - Możliwość tworzenia konta użytkownika z podstawowymi danymi (e-mail, hasło).
    - Logowanie do systemu za pomocą e-maila i hasła.
    - Opcja odzyskiwania zapomnianego hasła.

6. **Zabezpieczenia**
    - Wszystkie dane płatnicze powinny być przetwarzane w sposób bezpieczny, przy użyciu odpowiednich protokołów szyfrowania.
    - System powinien mieć zaimplementowane środki przeciwdziałające atakom typu DDoS oraz innym potencjalnym zagrożeniom dla bezpieczeństwa.

7. **Integracja z innymi systemami**
    - Możliwość integracji z systemami informacyjnymi przewoźników kolejowych w celu aktualizacji informacji o połączeniach, dostępności miejsc i opóźnieniach.

**Wymagania niefunkcjonalne dla systemu sprzedaży biletów pociągów**

1. **Wydajność**
    - System powinien być w stanie obsłużyć jednocześnie tysiące użytkowników bez degradacji jakości usług.
    - Odpowiedź systemu na zapytania użytkownika nie powinna przekraczać 3 sekund.

2. **Skalowalność**
    - Architektura systemu powinna pozwalać na łatwe skalowanie w zależności od obciążenia, zarówno wertykalnie, jak i horyzontalnie.

3. **Dostępność**
    - System powinien być dostępny 24/7 z poziomem dostępności SLA (Service Level Agreement) na poziomie 99,9%.
    - W przypadku awarii, czas przywrócenia działania systemu nie powinien przekraczać 1 godziny.

4. **Bezpieczeństwo**
    - Wszystkie dane przesyłane między klientem a serwerem powinny być szyfrowane przy użyciu protokołu TLS/SSL.
    - System powinien przechowywać hasła w sposób zahaszowany z użyciem nowoczesnych algorytmów (np. bcrypt).
    - Regularne testy penetracyjne, mające na celu wykrycie potencjalnych luk w zabezpieczeniach.

5. **Użyteczność**
    - Interfejs użytkownika powinien być prosty i intuicyjny, zaprojektowany zgodnie z zasadami UX.
    - System powinien być dostępny w co najmniej dwóch językach: polskim i angielskim.

6. **Integracja**
    - System powinien umożliwiać integrację z innymi popularnymi systemami płatniczymi oraz systemami informacyjnymi przewoźników.

7. **Backup i odzyskiwanie danych**
    - Regularne tworzenie kopii zapasowych baz danych.
    - Wdrożenie procedury odzyskiwania danych w przypadku awarii.

8. **Kompatybilność**
    - System powinien być kompatybilny z głównymi przeglądarkami internetowymi (Chrome, Firefox, Safari, Edge) oraz systemami operacyjnymi (Windows, macOS, Linux).
    - Poprawne działanie na urządzeniach mobilnych (smartfony, tablety) z różnymi rozdzielczościami ekranu.

9. **Rozszerzalność**
    - Architektura systemu powinna być modularna, umożliwiająca łatwe dodawanie nowych funkcji w przyszłości.

10. **Ochrona środowiska**
    - Infrastruktura hostingowa powinna być energooszczędna lub oparta na zielonej energii, minimalizując wpływ na środowisko.
