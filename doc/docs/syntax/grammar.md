---
sidebar_position: 1
---

# Essential Language Grammar

Grammatical Notation Formats (GNFs) are formal representations used to define the syntax and structure of programming
languages or other formal languages. GNFs, such as Backus-Naur Form (BNF), Extended Backus-Naur Form (EBNF), or other
similar notations, play a crucial role in language design and specification.

## Importance of Grammar Definition

The importance of GNFs for defining a language lies in their ability to provide a clear and unambiguous representation
of the language's syntax rules. Here are a few reasons why GNFs are significant:

1. **Formal Specification**: GNFs provide a formal and precise specification of the syntax of a language. They define
   the valid patterns and structures that make up the language's expressions, statements, and other language constructs.

2. **Clarity and Consistency**: GNFs help maintain clarity and consistency in language design. By using a standard
   notation, developers, language designers, and compiler writers can communicate and understand the language syntax
   effectively, ensuring consistent interpretation and implementation across different tools and environments.

3. **Parsing and Compilation**: GNFs are crucial for parsing and compiling source code written in the defined language.
   Compiler frontends or parsers use the language's grammar to analyze the structure of the code and generate an
   internal representation or Abstract Syntax Tree (AST) for further processing.

4. **Language Extensibility**: GNFs facilitate language extensibility. By defining a language's grammar, language
   designers can specify the rules and constraints for adding new features or extensions. This allows the language to
   evolve and adapt to changing requirements without sacrificing the integrity and consistency of the existing syntax.

5. **Tooling Support**: GNFs enable the development of various language tools and utilities. IDEs, code editors,
   linters, and other development tools leverage the grammar definition to provide syntax highlighting, code completion,
   error checking, and other language-specific features, enhancing the developer experience.

For more information on Backus-Naur Form (BNF), you can refer to
the [Wikipedia page](https://en.wikipedia.org/wiki/Backus%E2%80%93Naur_form).

In summary, GNFs are essential for defining a language's syntax and structure in a precise and unambiguous manner. They
serve as a foundation for language design, parsing, compilation, and tooling support, ensuring clarity, consistency, and
extensibility in language development.

# Grammar and its Importance for Language Definition

The grammar plays a vital role in language definition. It provides a structured way to define the syntax and structure
of a language. One commonly used notation for specifying grammar is the **GNF (Greibach Normal Form)** syntax.

## GNF Syntax

The GNF syntax includes the following elements:

- **Rule**: Defines a rule in the grammar.
- **Non-Terminal**: Represented by `"..."` or `'...'`, it defines a symbol that can be expanded into other symbols.
- **Terminal**: Represents a fixed string or token.
- **Concat**: Denoted by a space between symbols, it indicates concatenation.
- **Choice**: Denoted by `|`, it represents alternative options.
- **Optional**: Denoted by `?`, it signifies an element as optional.
- **0 or more**: Denoted by `*`, it indicates that an element can appear zero or more times.
- **1 or more**: Denoted by `+`, it indicates that an element must appear at least once.
- **Grouping**: Denoted by `(...)`, it groups elements together.
- **Exclusion**: Denoted by `-`, it excludes one element from another.
- **Comment**: Denoted by `//` for single-line comments and `/* ... */` for multi-line comments.
- **Character Set**: Denoted by `[...]`, it follows POSIX extended regular expression rules.

A well-defined grammar allows us to precisely specify the syntax and structure of a language. It serves as a foundation
for various language-related tasks such as parsing, code generation, and compiler construction.

For more information on BNF (Backus-Naur Form), a commonly used notation for grammar specification, you can refer to
the [Wikipedia page](https://en.wikipedia.org/wiki/Backus%E2%80%93Naur_form).

## Ooml Grammar

```bnf
CLASS_ID    = "class"
INTERFACE_ID= "interface"
PACKAGE_ID  = "package"
INHERITANCE_ID
            = "->"
ENUM_ID     = "enum"

RESERVED    = CLASS_ID
            | INTERFACE_ID
            | PACKAGE_ID
            | INHERITANCE_ID
            | ENUM_ID

// "- reserved" here signifies that a NAME cannot match the 'reserved' rule
NAME        = ([a-zA-Z_][a-zA-Z0-9_.]*) - RESERVED

SPACE       = [ \t]

EOF         = "\0"

// EOL means anything ending a line
EOL         = "\n"
            | "\n\r"

// We do not care if left and right are separated
// PAD is just a shorthand for PADDING
PADDING     = (SPACE | EOL)* comment (SPACE | EOL)*
PAD         = PADDING

// We need left and right to be separated
// SEP is just a shorthand for SEPARATOR
SEPARATOR   = (SPACE | EOL) PADDING
SEP         = SEPARATOR

/* Comments might be removed from the source file before the tokenizer,
   it will depend on the implementation we choose.
   This would allow them to be more flexible compare to the rather limited
   placement we have here.
*/
comment     = "//" [^\n\r\0]*
// Multiline comments
            | "/*" ([^\0] - "*/")*

// Starting rule
Start       = PAD list PAD EOF

list        = entry SEP list
            | entry

entry       = class
            | enum
            | interface
            | package
            | import

// FILE being any valid url
// (couldn't be bothered to do that one, pain in the a to specify properly)
import      = "@" FILE



/* Classes are always public? in java at least, so no access modifiers needed.
   This only holds for toplevel classes, but sub-classes have not been
   implemented yet. This might change anyway when we try to implement more
   languages.
*/
class       = restricted_access_modifier CLASS_ID SEP modifier_list NAME inheritance class_model


class_model = PAD "{" PAD class_prop_list "}"
            | PAD ":" PAD class_prop_list

class_prop_list
            = class_prop SEP class_prop_list
            | class_prop

class_prop  = attribute_with_access
            | method_with_access
            | constructor_with_access
            | access_block

restricted_access_modifier
            = "+"
            | ""

/* + for public
   - for private
   # for protected
*/
access_modifier
            = "+"
            | "-"
            | "#"


class_access_block
            = access_modifier PAD ":" PAD class_prop_block_list

class_prop_block_list
            = class_prop_block SEP class_prop_block_list
            | class_prop_block

class_prop_block
            = attribute
            | method
            | constructor

// The access modifier can be optional if we want to use the 'default'
// behaviour of java.
attribute_with_access
            = access_modifier attribute
            | attribute

attribute   = PAD modifier_list NAME PAD ":" PAD accessors PAD type

modifier_list
            = modifier SEP modifier_list
            | modifier SEP
            | ""

modifier    = NAME

accessors   = getter PAD setter
            | getter
            | setter PAD getter
            | setter
            | ""

getter      = "-"

setter      = "+"

method_with_access
            = access_modifier method
            | method

method      = modifier_list NAME PAD "(" argument_list PAD ")" PAD ":" PAD type

argument_list
            = PAD modifier_list NAME PAD ":" PAD type SEP argument_list
            | PAD modifier_list NAME PAD ":" PAD type

type        = NAME

constructor_with_access
            = access_modifier constructor
            | constructor

constructor = NAME PAD "(" argument_list PAD ")"

interface   = restricted_access_modifier INTERFACE_ID SEP modifier_list NAME inheritance interface_model

interface_model
            = PAD "{" PAD interface_prop_list "}"
            | PAD ":" PAD interface_prop_list

interface_prop_list
            = interface_prop SEP interface_prop_list
            | interface_prop

interface_prop
            = attribute_with_access
            | method_with_access
            | interface_access_block

interface_access_block
            = access_modifier PAD ":" PAD interface_prop_block_list

interface_prop_block_list
            = interface_prop_block SEP interface_prop_block_list
            | interface_prop_block

interface_prop_block
            = attribute
            | method

// This might need some polishing later.
package     = PACKAGE_ID SEP NAME

inheritance = PAD INHERITANCE_ID inheritance_list

inheritance_list
            = PAD NAME
            | PAD NAME PAD ',' inheritance_list

enum        = restricted_access_modifier ENUM_ID SEP NAME PAD enum_model

enum_model  = SEP ":" PAD enum_value_list
            | SEP "{" PAD enum_value_list PAD "}"

enum_value_list
            = PAD enum_key PAD "," enum_value_list
            | PAD enum_key


enum_key    = NAME
            | NAME PAD "=" PAD NAME
```
