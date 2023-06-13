---
sidebar_position: 2
---

# Ooml File Type

In the example Ooml file, the text-based representation of the object-oriented model defines classes, interfaces,
relationships, and other code elements using a syntax inspired by UML. The Ooml file serves as input to the Ooml Code
Gen tool, which reads, parses, and transpiles it into a structured code representation, enabling automated code
generation

```
/* 
  OOML - CodeGen
  - Default visibility (+)
  - Class visibilities (+, -)
  - Attrs, contructors & methods visibilities (+, -, #)
  - 
*/

@models.ooml
@controllers.ooml

package com.ecommerce.models // Optional

class Person 
  id: int
  # age: Integer
  - firstname: string
  + lastname: String
  - phone: double
  - address: Address[0..1]
  - products: Product[*] // A person can have several products
  - Person()
  + Person(id: int)
  # Person(id: int, age: Integer)
  + getId(): int
  + setName(name: string): void

+ class Address
- street: string
- person: Person[1]

- class Product
- id: int
- person: Person[1]

package com.ecommerce.controllers

class UserController
  + addUser(): Response
```