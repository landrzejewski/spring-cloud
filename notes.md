- dodać usługę shop-service pozwalającą na:
    - dodanie zamówienia (lista zawierająca id, ilość i cenę produktów)
    - podsumowanie całkowitej kwoty zamówienia
- zastosuj podejście modułowe (domain i adapters/spring)
- podłącz się do configuration-server i discovery-server

- dodaj nową implementację adaptera usługi TimeProvider, tak aby czas/znacznik czasowy był pobierany z
  zewnętrznego api http://worldtimeapi.org/api/timezone/Europe/Warsaw
