`> [14, 0] Program
   +> ListDeclClass [List with 0 elements]
   `> [14, 0] Main
      +> [16, 5] ListDeclVar [List with 5 elements]
      |  []> [16, 5] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  +> [16, 5] Identifier (i)
      |  ||  `> NoInitialization
      |  []> [16, 7] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  +> [16, 7] Identifier (j)
      |  ||  `> [16, 8] Initialization
      |  ||     `> [16, 9] Int (0)
      |  []> [16, 11] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  +> [16, 11] Identifier (k)
      |  ||  `> [16, 13] Initialization
      |  ||     `> [16, 15] Int (12)
      |  []> [16, 18] DeclVar
      |  ||  +> [16, 1] Identifier (int)
      |  ||  +> [16, 18] Identifier (l)
      |  ||  `> NoInitialization
      |  []> [16, 20] DeclVar
      |      +> [16, 1] Identifier (int)
      |      +> [16, 20] Identifier (m)
      |      `> NoInitialization
      `> ListInst [List with 23 elements]
         []> [18, 1] Assign
         ||  +> [18, 1] Identifier (i)
         ||  `> [18, 5] Minus
         ||     +> [18, 5] Plus
         ||     |  +> [18, 5] Identifier (i)
         ||     |  `> [18, 9] Identifier (j)
         ||     `> [18, 13] Identifier (k)
         []> [19, 1] Assign
         ||  +> [19, 1] Identifier (i)
         ||  `> [19, 5] Minus
         ||     +> [19, 5] Plus
         ||     |  +> [19, 5] Int (1)
         ||     |  `> [19, 9] Int (1)
         ||     `> [19, 13] Int (1)
         []> [19, 15] NoOperation
         []> [20, 1] NoOperation
         []> [20, 2] NoOperation
         []> [21, 1] Minus
         ||  +> [21, 1] MethodCall
         ||  |  +> [21, 1] This
         ||  |  +> [21, 1] Identifier (i)
         ||  |  `> [21, 4] ListExpr [List with 1 elements]
         ||  |     []> [21, 4] Plus
         ||  |         +> [21, 4] Int (1)
         ||  |         `> [21, 8] Int (1)
         ||  `> [21, 13] Int (1)
         []> [22, 1] Assign
         ||  +> [22, 1] Identifier (i)
         ||  `> [22, 5] UnaryMinus
         ||     `> [22, 6] Int (1)
         []> [24, 1] NoOperation
         []> [24, 2] NoOperation
         []> [24, 3] NoOperation
         []> [25, 1] NoOperation
         []> [27, 1] Assign
         ||  +> [27, 1] Identifier (i)
         ||  `> [27, 5] Divide
         ||     +> [27, 5] Multiply
         ||     |  +> [27, 5] Int (4)
         ||     |  `> [27, 9] Int (3)
         ||     `> [27, 13] Int (2)
         []> [28, 1] Assign
         ||  +> [28, 1] Identifier (j)
         ||  `> [28, 5] Divide
         ||     +> [28, 5] Multiply
         ||     |  +> [28, 5] Identifier (i)
         ||     |  `> [28, 9] Identifier (k)
         ||     `> [28, 13] Identifier (j)
         []> [30, 1] Assign
         ||  +> [30, 1] Identifier (i)
         ||  `> [30, 5] Plus
         ||     +> [30, 5] Minus
         ||     |  +> [30, 6] Int (1)
         ||     |  `> [30, 8] Int (1)
         ||     `> [30, 13] Multiply
         ||        +> [30, 13] Multiply
         ||        |  +> [30, 14] Int (1)
         ||        |  `> [30, 16] Int (1)
         ||        `> [30, 21] Minus
         ||           +> [30, 23] Divide
         ||           |  +> [30, 24] Divide
         ||           |  |  +> [30, 25] Int (2)
         ||           |  |  `> [30, 27] Int (1)
         ||           |  `> [30, 30] Plus
         ||           |     +> [30, 31] Int (0)
         ||           |     `> [30, 33] Int (1)
         ||           `> [30, 39] UnaryMinus
         ||              `> [30, 41] Int (0)
         []> [31, 1] Assign
         ||  +> [31, 1] Identifier (i)
         ||  `> [31, 5] Modulo
         ||     +> [31, 5] Int (19)
         ||     `> [31, 10] Int (7)
         []> [32, 1] Assign
         ||  +> [32, 1] Identifier (k)
         ||  `> [32, 5] Modulo
         ||     +> [32, 5] Identifier (j)
         ||     `> [32, 9] Identifier (i)
         []> [34, 1] NoOperation
         []> [35, 1] NoOperation
         []> [35, 2] NoOperation
         []> [35, 3] NoOperation
         []> [35, 4] NoOperation
         []> [37, 1] Assign
         ||  +> [37, 1] Identifier (i)
         ||  `> [37, 5] Assign
         ||     +> [37, 5] Identifier (j)
         ||     `> [37, 9] Identifier (k)
         []> [38, 1] Println
             `> [38, 9] ListExpr [List with 3 elements]
                []> [38, 9] Identifier (i)
                []> [38, 11] Identifier (j)
                []> [38, 13] Identifier (k)
