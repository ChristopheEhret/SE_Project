`> [16, 0] Program
   +> [19, 0] ListDeclClass [List with 2 elements]
   |  []> [16, 0] DeclClass
   |  ||  +> [16, 6] Identifier (A)
   |  ||  +> Identifier (Object)
   |  ||  +> ListDeclField [List with 0 elements]
   |  ||  `> ListDeclMethod [List with 0 elements]
   |  []> [19, 0] DeclClass
   |      +> [19, 6] Identifier (B)
   |      +> Identifier (Object)
   |      +> [31, 18] ListDeclField [List with 10 elements]
   |      |  []> [23, 5] DeclField
   |      |  ||  +> [23, 1] Identifier (int)
   |      |  ||  +> [23, 5] Identifier (a)
   |      |  ||  `> NoInitialization
   |      |  []> [24, 17] DeclField
   |      |  ||  +> [24, 11] Identifier (float)
   |      |  ||  +> [24, 17] Identifier (b)
   |      |  ||  `> NoInitialization
   |      |  []> [25, 9] DeclField
   |      |  ||  +> [25, 1] Identifier (boolean)
   |      |  ||  +> [25, 9] Identifier (c)
   |      |  ||  `> NoInitialization
   |      |  []> [25, 12] DeclField
   |      |  ||  +> [25, 1] Identifier (boolean)
   |      |  ||  +> [25, 12] Identifier (d)
   |      |  ||  `> [25, 13] Initialization
   |      |  ||     `> [25, 14] BooleanLiteral (true)
   |      |  []> [25, 19] DeclField
   |      |  ||  +> [25, 1] Identifier (boolean)
   |      |  ||  +> [25, 19] Identifier (e)
   |      |  ||  `> NoInitialization
   |      |  []> [25, 21] DeclField
   |      |  ||  +> [25, 1] Identifier (boolean)
   |      |  ||  +> [25, 21] Identifier (f)
   |      |  ||  `> NoInitialization
   |      |  []> [25, 23] DeclField
   |      |  ||  +> [25, 1] Identifier (boolean)
   |      |  ||  +> [25, 23] Identifier (g)
   |      |  ||  `> [25, 24] Initialization
   |      |  ||     `> [25, 25] BooleanLiteral (false)
   |      |  []> [26, 7] DeclField
   |      |  ||  +> [26, 1] Identifier (float)
   |      |  ||  +> [26, 7] Identifier (h)
   |      |  ||  `> NoInitialization
   |      |  []> [31, 18] DeclField
   |      |  ||  +> [31, 11] Identifier (string)
   |      |  ||  +> [31, 18] Identifier (i)
   |      |  ||  `> NoInitialization
   |      |  []> [31, 20] DeclField
   |      |      +> [31, 11] Identifier (string)
   |      |      +> [31, 20] Identifier (j)
   |      |      `> [31, 21] Initialization
   |      |         `> [31, 22] StringLiteral (chaine)
   |      `> ListDeclMethod [List with 6 elements]
   |         []> [20, 1] DeclMethod
   |         ||  +> [20, 1] Identifier (void)
   |         ||  +> [20, 6] Identifier (f1)
   |         ||  +> ListDeclParam [List with 0 elements]
   |         ||  `> [20, 11] MethodBody
   |         ||     +> ListDeclVar [List with 0 elements]
   |         ||     `> ListInst [List with 0 elements]
   |         []> [22, 1] DeclMethod
   |         ||  +> [22, 1] Identifier (void)
   |         ||  +> [22, 6] Identifier (f2)
   |         ||  +> [22, 9] ListDeclParam [List with 1 elements]
   |         ||  |  []> [22, 9] DeclParam
   |         ||  |      +> [22, 9] Identifier (int)
   |         ||  |      `> [22, 13] Identifier (p1)
   |         ||  `> [22, 22] MethodAsm
   |         ||     `> [22, 22] StringLiteral (test)
   |         []> [27, 1] DeclMethod
   |         ||  +> [27, 1] Identifier (int)
   |         ||  +> [27, 5] Identifier (sum1)
   |         ||  +> [27, 10] ListDeclParam [List with 2 elements]
   |         ||  |  []> [27, 10] DeclParam
   |         ||  |  ||  +> [27, 10] Identifier (int)
   |         ||  |  ||  `> [27, 14] Identifier (p1)
   |         ||  |  []> [27, 18] DeclParam
   |         ||  |      +> [27, 18] Identifier (int)
   |         ||  |      `> [27, 22] Identifier (p2)
   |         ||  `> [27, 26] MethodBody
   |         ||     +> ListDeclVar [List with 0 elements]
   |         ||     `> ListInst [List with 2 elements]
   |         ||        []> [28, 2] Println
   |         ||        ||  `> [28, 10] ListExpr [List with 1 elements]
   |         ||        ||     []> [28, 10] StringLiteral (Corps explicite methode 1)
   |         ||        []> [29, 2] Return
   |         ||            `> [29, 9] Plus
   |         ||               +> [29, 9] Identifier (p1)
   |         ||               `> [29, 12] Identifier (p2)
   |         []> [32, 1] DeclMethod
   |         ||  +> [32, 1] Identifier (float)
   |         ||  +> [32, 7] Identifier (sum2)
   |         ||  +> [32, 12] ListDeclParam [List with 2 elements]
   |         ||  |  []> [32, 12] DeclParam
   |         ||  |  ||  +> [32, 12] Identifier (int)
   |         ||  |  ||  `> [32, 16] Identifier (p1)
   |         ||  |  []> [32, 20] DeclParam
   |         ||  |      +> [32, 20] Identifier (float)
   |         ||  |      `> [32, 26] Identifier (p2)
   |         ||  `> [32, 30] MethodBody
   |         ||     +> ListDeclVar [List with 0 elements]
   |         ||     `> ListInst [List with 2 elements]
   |         ||        []> [33, 2] Println
   |         ||        ||  `> [33, 10] ListExpr [List with 1 elements]
   |         ||        ||     []> [33, 10] StringLiteral (Corps explicite methode 2)
   |         ||        []> [34, 2] Return
   |         ||            `> [34, 9] Plus
   |         ||               +> [34, 9] Identifier (p1)
   |         ||               `> [34, 12] Identifier (p2)
   |         []> [36, 1] DeclMethod
   |         ||  +> [36, 1] Identifier (boolean)
   |         ||  +> [36, 9] Identifier (f3)
   |         ||  +> [36, 12] ListDeclParam [List with 3 elements]
   |         ||  |  []> [36, 12] DeclParam
   |         ||  |  ||  +> [36, 12] Identifier (int)
   |         ||  |  ||  `> [36, 16] Identifier (p1)
   |         ||  |  []> [36, 20] DeclParam
   |         ||  |  ||  +> [36, 20] Identifier (boolean)
   |         ||  |  ||  `> [36, 28] Identifier (p2)
   |         ||  |  []> [36, 32] DeclParam
   |         ||  |      +> [36, 32] Identifier (string)
   |         ||  |      `> [36, 39] Identifier (p3)
   |         ||  `> [36, 43] MethodBody
   |         ||     +> [37, 10] ListDeclVar [List with 1 elements]
   |         ||     |  []> [37, 10] DeclVar
   |         ||     |      +> [37, 2] Identifier (boolean)
   |         ||     |      +> [37, 10] Identifier (inner_var)
   |         ||     |      `> [37, 20] Initialization
   |         ||     |         `> [37, 22] Identifier (p2)
   |         ||     `> ListInst [List with 2 elements]
   |         ||        []> [38, 2] Println
   |         ||        ||  `> [38, 10] ListExpr [List with 1 elements]
   |         ||        ||     []> [38, 10] StringLiteral (Corps explicite methode 3)
   |         ||        []> [39, 2] Return
   |         ||            `> [39, 9] Or
   |         ||               +> [39, 9] Equals
   |         ||               |  +> [39, 9] Identifier (p1)
   |         ||               |  `> [39, 15] Int (0)
   |         ||               `> [39, 20] Identifier (inner_var)
   |         []> [41, 1] DeclMethod
   |             +> [41, 1] Identifier (void)
   |             +> [41, 6] Identifier (f4)
   |             +> [41, 9] ListDeclParam [List with 1 elements]
   |             |  []> [41, 9] DeclParam
   |             |      +> [41, 9] Identifier (A)
   |             |      `> [41, 11] Identifier (a)
   |             `> [41, 14] MethodBody
   |                +> ListDeclVar [List with 0 elements]
   |                `> ListInst [List with 2 elements]
   |                   []> [42, 2] Println
   |                   ||  `> [42, 10] ListExpr [List with 1 elements]
   |                   ||     []> [42, 10] StringLiteral (Corps explicite methode 4)
   |                   []> [43, 2] Return
   |                       `> [43, 9] Null
   `> EmptyMain
