`> [23, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [23, 0] Main
      +> [26, 9] ListDeclVar [List with 3 elements]
      |  []> [25, 9] DeclVar
      |  ||  +> [25, 1] Identifier (boolean)
      |  ||  +> [25, 9] Identifier (t)
      |  ||  `> [25, 10] Initialization
      |  ||     `> [25, 11] BooleanLiteral (true)
      |  []> [25, 17] DeclVar
      |  ||  +> [25, 1] Identifier (boolean)
      |  ||  +> [25, 17] Identifier (f)
      |  ||  `> [25, 19] Initialization
      |  ||     `> [25, 21] BooleanLiteral (false)
      |  []> [26, 9] DeclVar
      |      +> [26, 1] Identifier (boolean)
      |      +> [26, 9] Identifier (res)
      |      `> NoInitialization
      `> ListInst [List with 11 elements]
         []> [28, 1] Println
         ||  `> [28, 9] ListExpr [List with 2 elements]
         ||     []> [28, 9] Not
         ||     ||  `> [28, 10] Identifier (t)
         ||     []> [28, 12] Not
         ||         `> [28, 13] Identifier (f)
         []> [29, 1] Println
         ||  `> [29, 9] ListExpr [List with 4 elements]
         ||     []> [29, 9] Or
         ||     ||  +> [29, 9] Identifier (t)
         ||     ||  `> [29, 14] Identifier (t)
         ||     []> [29, 16] Or
         ||     ||  +> [29, 16] Identifier (t)
         ||     ||  `> [29, 21] Identifier (f)
         ||     []> [29, 23] Or
         ||     ||  +> [29, 23] Identifier (f)
         ||     ||  `> [29, 28] Identifier (t)
         ||     []> [29, 30] Or
         ||         +> [29, 30] Identifier (f)
         ||         `> [29, 35] Identifier (f)
         []> [30, 1] Println
         ||  `> [30, 9] ListExpr [List with 4 elements]
         ||     []> [30, 9] And
         ||     ||  +> [30, 9] Identifier (t)
         ||     ||  `> [30, 14] Identifier (t)
         ||     []> [30, 16] And
         ||     ||  +> [30, 16] Identifier (t)
         ||     ||  `> [30, 21] Identifier (f)
         ||     []> [30, 23] And
         ||     ||  +> [30, 23] Identifier (f)
         ||     ||  `> [30, 28] Identifier (t)
         ||     []> [30, 30] And
         ||         +> [30, 30] Identifier (f)
         ||         `> [30, 35] Identifier (f)
         []> [31, 1] Println
         ||  `> [31, 9] ListExpr [List with 2 elements]
         ||     []> [31, 9] Equals
         ||     ||  +> [31, 9] Int (1)
         ||     ||  `> [31, 12] Int (1)
         ||     []> [31, 14] Equals
         ||         +> [31, 14] Int (1)
         ||         `> [31, 17] Int (2)
         []> [32, 1] Println
         ||  `> [32, 9] ListExpr [List with 2 elements]
         ||     []> [32, 9] NotEquals
         ||     ||  +> [32, 9] Int (1)
         ||     ||  `> [32, 12] Int (1)
         ||     []> [32, 14] NotEquals
         ||         +> [32, 14] Int (1)
         ||         `> [32, 17] Int (2)
         []> [33, 1] Println
         ||  `> [33, 9] ListExpr [List with 3 elements]
         ||     []> [33, 9] Lower
         ||     ||  +> [33, 9] Int (1)
         ||     ||  `> [33, 11] Int (1)
         ||     []> [33, 13] Lower
         ||     ||  +> [33, 13] Int (1)
         ||     ||  `> [33, 15] Int (0)
         ||     []> [33, 17] Lower
         ||         +> [33, 17] Int (1)
         ||         `> [33, 19] Int (2)
         []> [34, 1] Println
         ||  `> [34, 9] ListExpr [List with 3 elements]
         ||     []> [34, 9] Greater
         ||     ||  +> [34, 9] Int (1)
         ||     ||  `> [34, 11] Int (1)
         ||     []> [34, 13] Greater
         ||     ||  +> [34, 13] Int (1)
         ||     ||  `> [34, 15] Int (0)
         ||     []> [34, 17] Greater
         ||         +> [34, 17] Int (1)
         ||         `> [34, 19] Int (2)
         []> [35, 1] Println
         ||  `> [35, 9] ListExpr [List with 3 elements]
         ||     []> [35, 9] LowerOrEqual
         ||     ||  +> [35, 9] Int (1)
         ||     ||  `> [35, 12] Int (1)
         ||     []> [35, 14] LowerOrEqual
         ||     ||  +> [35, 14] Int (1)
         ||     ||  `> [35, 17] Int (0)
         ||     []> [35, 19] LowerOrEqual
         ||         +> [35, 19] Int (1)
         ||         `> [35, 22] Int (2)
         []> [36, 1] Println
         ||  `> [36, 9] ListExpr [List with 3 elements]
         ||     []> [36, 9] GreaterOrEqual
         ||     ||  +> [36, 9] Int (1)
         ||     ||  `> [36, 12] Int (1)
         ||     []> [36, 14] GreaterOrEqual
         ||     ||  +> [36, 14] Int (1)
         ||     ||  `> [36, 17] Int (0)
         ||     []> [36, 19] GreaterOrEqual
         ||         +> [36, 19] Int (1)
         ||         `> [36, 22] Int (2)
         []> [38, 1] Assign
         ||  +> [38, 1] Identifier (res)
         ||  `> [38, 7] Or
         ||     +> [38, 7] Identifier (f)
         ||     `> [38, 12] And
         ||        +> [38, 14] Identifier (t)
         ||        `> [38, 19] Not
         ||           `> [38, 20] Identifier (f)
         []> [39, 1] Println
             `> [39, 9] ListExpr [List with 1 elements]
                []> [39, 9] Identifier (res)
