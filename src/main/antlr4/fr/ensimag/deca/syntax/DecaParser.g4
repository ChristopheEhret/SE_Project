parser grammar DecaParser;

options {
    // Default language but name it anyway
    //
    language  = Java;

    // Use a superclass to implement all helper
    // methods, instance variables and overrides
    // of ANTLR default methods, such as error
    // handling.
    //
    superClass = AbstractDecaParser;

    // Use the vocabulary generated by the accompanying
    // lexer. Maven knows how to work out the relationship
    // between the lexer and parser and will build the
    // lexer before the parser. It will also rebuild the
    // parser if the lexer changes.
    //
    tokenVocab = DecaLexer;

}

// which packages should be imported?
@header {
    import fr.ensimag.deca.tree.*;
    import java.io.PrintStream;
    import java.lang.String;
    import fr.ensimag.deca.tools.SymbolTable;
}

@members {
    @Override
    protected AbstractProgram parseProgram() {
        return prog().tree;
    }
}

prog returns[AbstractProgram tree]
    : list_classes main EOF {
            assert($list_classes.tree != null);
            assert($main.tree != null);
            $tree = new Program($list_classes.tree, $main.tree);
            setLocation($tree, $list_classes.start);
        }
    ;

main returns[AbstractMain tree]
    : /* epsilon */ {
            $tree = new EmptyMain();
        }
    | block {
            assert($block.decls != null);
            assert($block.insts != null);
            $tree = new Main($block.decls, $block.insts);
            setLocation($tree, $block.start);
        }
    ;

block returns[ListDeclVar decls, ListInst insts]
    : OBRACE list_decl list_inst CBRACE {
            assert($list_decl.tree != null);
            assert($list_inst.tree != null);
            $decls = $list_decl.tree;
            $insts = $list_inst.tree;
        }
    ;

list_decl returns[ListDeclVar tree]
@init   {
            $tree = new ListDeclVar();
        }
    : decl_var_set[$tree]*
    ;

decl_var_set[ListDeclVar l]
    : type list_decl_var[$l,$type.tree] SEMI
    ;

list_decl_var[ListDeclVar l, AbstractIdentifier t]
    : dv1=decl_var[$t] {
		assert($dv1.tree != null);
		$l.add($dv1.tree);
		setLocation($l,$dv1.start);
        } (COMMA dv2=decl_var[$t] {
        assert($dv2.tree != null);
        $l.add($dv2.tree);
        }
      )*
    ;
decl_var[AbstractIdentifier t] returns[AbstractDeclVar tree]
@init   {
	// On l'instancie en NoInitialization dans le cas où on rentre pas dans EQUALS expr
			AbstractInitialization initialization = new NoInitialization();
        }
    : i=ident {
    		assert($i.tree != null);
        }
      (EQUALS e=expr {
      		assert($expr.tree != null);
      		initialization = new Initialization($e.tree);
      		setLocation(initialization,$EQUALS);
        }
      )? {
      		$tree = new DeclVar($t,$i.tree,initialization);
       		setLocation($tree,$i.start);
        }
    ;

list_inst returns[ListInst tree]
@init {
	$tree= new ListInst();
}
    : (inst {
    	assert($inst.tree != null);
    	$tree.add($inst.tree);
        }
      )*
    ;

inst returns[AbstractInst tree]
    : e1=expr SEMI {
        assert($e1.tree != null);
        $tree = $e1.tree;
        setLocation($tree,$expr.start);
        }
    | SEMI {
		$tree = new NoOperation();
		setLocation($tree,$SEMI);
        }
    | PRINT OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(false,$list_expr.tree);
            setLocation($tree,$PRINT);
        }
    | PRINTLN OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(false,$list_expr.tree);
            setLocation($tree,$PRINTLN);
        }
    | PRINTX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Print(true,$list_expr.tree);
            setLocation($tree,$PRINTX);
        }
    | PRINTLNX OPARENT list_expr CPARENT SEMI {
            assert($list_expr.tree != null);
            $tree = new Println(true,$list_expr.tree);
            setLocation($tree,$PRINTLNX);

        }
    | if_then_else {
            assert($if_then_else.tree != null);
            $tree=$if_then_else.tree;
            setLocation($tree,$if_then_else.start);
        }
    | WHILE OPARENT condition=expr CPARENT OBRACE body=list_inst CBRACE {
            assert($condition.tree != null);
            assert($body.tree != null);
            $tree = new While($condition.tree,$body.tree);
            setLocation($tree,$WHILE);
        }
    | RETURN expr SEMI {
            assert($expr.tree != null);
            $tree = new Return($expr.tree);
            setLocation($tree,$RETURN);

        }
    | DISPLAY OPARENT str=expr COMMA posy=expr COMMA posx=expr CPARENT SEMI {
    		assert($str.tree != null);
    		assert($posy.tree != null);
    		assert($posx.tree != null);
    		$tree = new Display($str.tree, $posy.tree, $posx.tree);
    		setLocation($tree,$DISPLAY);
    	}
    ;
if_then_else returns[IfThenElse tree]
@init {
	IfThenElse newElseIf = null;
	boolean presence_elsif = false;
	ListInst oldBranch = new ListInst();
}
    : if1=IF OPARENT condition=expr CPARENT OBRACE li_if=list_inst CBRACE {
    		assert($condition.tree != null);
    		assert($li_if.tree != null);
    		$tree = new IfThenElse($condition.tree,$li_if.tree,oldBranch);
    		setLocation($tree,$if1);
        }
      (ELSE elsif=IF OPARENT elsif_cond=expr CPARENT OBRACE elsif_li=list_inst CBRACE {
        	assert($elsif_cond.tree != null);
        	assert($elsif_li.tree != null);
        	presence_elsif=true;
        	ListInst newBranch = new ListInst();
        	newElseIf = new IfThenElse($elsif_cond.tree,$elsif_li.tree,newBranch);
        	oldBranch.add(newElseIf);
        	oldBranch=newBranch;
        	setLocation(newElseIf,$ELSE);
        }
      )*
      (ELSE OBRACE li_else=list_inst CBRACE {
      		assert($li_else.tree != null);
      		if(!presence_elsif){
      			$tree.setElseBranch($li_else.tree);
      		}
      		else{
      	       	newElseIf.setElseBranch($li_else.tree);
      		}
        }
      )?
    ;

list_expr returns[ListExpr tree]
@init  {
			$tree = new ListExpr();
        }
    : (e1=expr {
    		assert($e1.tree != null);
    		$tree.add($e1.tree);
    		setLocation($tree,$e1.start);
        }
       (COMMA e2=expr {
      	    assert($e2.tree != null);
    		$tree.add($e2.tree);
        }
       )* )?
    ;

expr returns[AbstractExpr tree]
    : assign_expr {
            assert($assign_expr.tree != null);
            $tree = $assign_expr.tree;
            setLocation($tree,$assign_expr.start);
        }
    ;

assign_expr returns[AbstractExpr tree]
    : e=or_expr (
        /* condition: expression e must be a "LVALUE" */ {
            if (! ($e.tree instanceof AbstractLValue)) {
                throw new InvalidLValue(this, $ctx);
            }
        }
        EQUALS e2=assign_expr {
            assert($e.tree != null);
            assert($e2.tree != null);
        	$tree = new Assign((AbstractLValue)$e.tree,$e2.tree);
        	setLocation($tree,$e.start);
        }
      | /* epsilon */ {
            assert($e.tree != null);
            $tree = $e.tree;
        }
      )
    ;

or_expr returns[AbstractExpr tree]
    : e=and_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    | e1=or_expr OR e2=and_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Or($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
       }
    ;

and_expr returns[AbstractExpr tree]
    : e=eq_neq_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    |  e1=and_expr AND e2=eq_neq_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new And($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    ;

eq_neq_expr returns[AbstractExpr tree]
    : e=inequality_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    | e1=eq_neq_expr EQEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Equals($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=eq_neq_expr NEQ e2=inequality_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new NotEquals($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    ;

inequality_expr returns[AbstractExpr tree]
    : e=sum_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    | e1=inequality_expr LEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new LowerOrEqual($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=inequality_expr GEQ e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new GreaterOrEqual($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=inequality_expr GT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Greater($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=inequality_expr LT e2=sum_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Lower($e1.tree,$e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=inequality_expr INSTANCEOF type {
            assert($e1.tree != null);
            assert($type.tree != null);
            $tree = new InstanceOf($e1.tree,$type.tree);
            setLocation($tree,$e1.start);
        }
    ;


sum_expr returns[AbstractExpr tree]
    : e=mult_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    | e1=sum_expr PLUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Plus($e1.tree, $e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=sum_expr MINUS e2=mult_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Minus($e1.tree, $e2.tree);
            setLocation($tree,$e1.start);
        }
    ;

mult_expr returns[AbstractExpr tree]
    : e=unary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
    | e1=mult_expr TIMES e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Multiply($e1.tree, $e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=mult_expr SLASH e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Divide($e1.tree, $e2.tree);
            setLocation($tree,$e1.start);
        }
    | e1=mult_expr PERCENT e2=unary_expr {
            assert($e1.tree != null);
            assert($e2.tree != null);
            $tree = new Modulo($e1.tree, $e2.tree);
            setLocation($tree,$e1.start);
        }
    ;

unary_expr returns[AbstractExpr tree]
    : op=MINUS e=unary_expr {
            assert($e.tree != null);
            $tree = new UnaryMinus($e.tree);
            setLocation($tree,$op);
        }
    | op=EXCLAM e=unary_expr {
            assert($e.tree != null);
            $tree = new Not($e.tree);
            setLocation($tree,$op);
        }
    | select_expr {
            assert($select_expr.tree != null);
            $tree = $select_expr.tree;
            setLocation($tree,$select_expr.start);
        }
    ;

select_expr returns[AbstractExpr tree]
    : e=primary_expr {
            assert($e.tree != null);
            $tree = $e.tree;
            setLocation($tree,$e.start);
        }
        // La règle suivante permet d'appeler une méthode d'une classe
    | e1=select_expr DOT i=ident {
            assert($e1.tree != null);
            assert($i.tree != null);
        }
        (o=OPARENT args=list_expr CPARENT {
            // we matched "e1.i(args)"
            assert($args.tree != null);
            $tree = new MethodCall($e1.tree,$i.tree,$args.tree);
            setLocation($tree,$e1.start);
        }
        | /* epsilon */ {
        	$tree = new Selection($e1.tree,$i.tree);
        	setLocation($tree,$e1.start);
            // we matched "e.i"
        }
        )
    ;
primary_expr returns[AbstractExpr tree]
    : ident {
            assert($ident.tree != null);
            $tree = $ident.tree;
            setLocation($tree,$ident.start);
        }
// Cette règle permet d'appeler une méthode directement sans passer par une classe
// Cela est utile lorsqu'une méthode a besoin d'appeler une autre méthode présente dans
//la même classe
    | m=ident OPARENT args=list_expr CPARENT {
            assert($args.tree != null);
            assert($m.tree != null);
            This thiss = new This(true);
           	setLocation(thiss,$m.start);
            $tree = new MethodCall(thiss,$m.tree,$args.tree);
            setLocation($tree,$m.start);
        }
    | OPARENT expr CPARENT {
            assert($expr.tree != null);
            $tree = $expr.tree;
            setLocation($tree,$OPARENT);
        }
    | READINT OPARENT CPARENT {
    		$tree = new ReadInt();
    		setLocation($tree,$READINT);
        }
    | READFLOAT OPARENT CPARENT {
    		$tree = new ReadFloat();
    		setLocation($tree,$READFLOAT);
        }
    | NEW ident OPARENT CPARENT {
            assert($ident.tree != null);
            $tree = new New($ident.tree);
       		setLocation($tree,$NEW);
        }
    | cast=OPARENT type CPARENT OPARENT expr CPARENT {
            assert($type.tree != null);
            assert($expr.tree != null);
            $tree = new Cast($type.tree,$expr.tree);
            setLocation($tree,$cast);
        }
    | GETKEY OPARENT CPARENT {
    		$tree = new GetKey();
    		setLocation($tree,$GETKEY);
    	}
    | literal {
            assert($literal.tree != null);
            $tree = $literal.tree;
            setLocation($tree,$literal.start);
        }
    ;

type returns[AbstractIdentifier tree]
    : ident {
            assert($ident.tree != null);
            $tree= $ident.tree;
            setLocation($tree,$ident.start);
        }
    ;

literal returns[AbstractExpr tree]
    : INT {
            try {
                $tree = new IntLiteral(Integer.parseInt($INT.text));
            } catch (NumberFormatException e) {
                // The integer could not be parsed (probably it's too large).
                // set $tree to null, and then fail with the semantic predicate
                // {$tree != null}?. In decac, we'll have a more advanced error
                // management.
                $tree = null;
                throw new IntegerTooLarge(this,$ctx);
            }
              setLocation($tree,$INT);
        } {$tree != null}?

    | fd=FLOAT {
			try {
    			$tree = new FloatLiteral(Float.parseFloat($fd.text));
            } 
            catch (NumberFormatException e) {
                // The float could not be parsed (probably it's too large).
                // set $tree to null, and then fail with the semantic predicate
                // {$tree != null}?. In decac, we'll have a more advanced error
                // management.
                $tree = null;
                throw new FloatTooLarge(this,$ctx);             
            }
            catch (IllegalArgumentException i) {
            	$tree = null;
            	throw new FloatTooLarge(this,$ctx);                       
            }
              setLocation($tree,$fd);
        } {$tree != null}?
    | STRING {
              $tree = new StringLiteral($STRING.text.substring(1,$STRING.text.length()-1));
      		  setLocation($tree,$STRING);
        } {$tree != null}?
    | TRUE {
			$tree = new BooleanLiteral(true);
			setLocation($tree,$TRUE);
        }
    | FALSE {
    		$tree = new BooleanLiteral(false);
    		setLocation($tree,$FALSE);
        } {$tree != null}?
    | THIS {
    		$tree = new This(false);
    		setLocation($tree,$THIS);
        } {$tree != null}?
    | NULL {
    		$tree = new Null();
    		setLocation($tree,$NULL);
        } 
    ;

ident returns[AbstractIdentifier tree]
    : IDENT {
    		$tree = new Identifier(this.getDecacCompiler().getOrAddSymbol($IDENT.text));
    		setLocation($tree,$IDENT);
        }
    ;

/****     Class related rules     ****/

list_classes returns[ListDeclClass tree]
  @init{
  		$tree = new ListDeclClass();
  }
    :
      (c1=class_decl {
      		assert($c1.tree != null);
        	$tree.add($c1.tree);
        	setLocation($tree,$c1.start);
        }
      )*
    ;

class_decl returns[AbstractDeclClass tree]
    : CLASS name=ident superclass=class_extension OBRACE class_body CBRACE {
			assert($name.tree != null);
    		assert($superclass.tree != null);
    		assert($class_body.fields != null);
    		assert($class_body.methods != null);
    		$tree = new DeclClass($name.tree,$superclass.tree,$class_body.fields,$class_body.methods);
    		setLocation($tree,$CLASS);
        }
    ;

class_extension returns[AbstractIdentifier tree]
    : EXTENDS ident {
    		assert($ident.tree != null);
    		$tree = $ident.tree;
    		setLocation($tree,$EXTENDS);
        }
    | /* epsilon */ {
			$tree = new Identifier(this.getDecacCompiler().getOrAddSymbol("Object"));   		
        }
    ;

class_body returns[ListDeclField fields,ListDeclMethod methods]
 @init{
	 $fields = new ListDeclField();
	 $methods = new ListDeclMethod(); 
 }    
    : (m=decl_method {
    		assert($m.tree != null);	
			$methods.add($m.tree);
        }
      | decl_field_set[$fields]
      )*
    ;

decl_field_set[ListDeclField fields]
    : v=visibility t=type list_decl_field[$visibility.tree,$t.tree,$fields]
      SEMI
    ;

visibility returns[Visibility tree]
    : /* epsilon */ {
    		$tree = Visibility.PUBLIC;
        }
    | PROTECTED {
    		$tree = Visibility.PROTECTED;
        }
    ;

list_decl_field[Visibility vis,AbstractIdentifier t,ListDeclField fields] 
    : dv1=decl_field[$vis,$t]{
      	assert($dv1.tree != null);
      	fields.add($dv1.tree);
      	setLocation($fields,$dv1.start);
      }
        (COMMA dv2=decl_field[$vis,$t]{
        	assert($dv2.tree != null);
        	fields.add($dv2.tree);
        }
      )*
    ;

decl_field[Visibility vis,AbstractIdentifier t] returns[AbstractDeclField tree]
@init {
	AbstractInitialization initialization = new NoInitialization();
} 
    : i=ident {
    		assert($i.tree != null);
        }
      (EQUALS e=expr {
      		assert($expr.tree != null);
      		initialization = new Initialization($e.tree);
      		setLocation(initialization,$EQUALS);
        }
      )? {
      		$tree = new DeclField($i.tree,$t,initialization,vis);
      		setLocation($tree,$i.start);
        }
    ;

decl_method returns[AbstractDeclMethod tree]
@init {
	AbstractMethodBody body  = null;
}
    : type ident OPARENT params=list_params CPARENT (block {
 			assert($block.decls != null);
 			assert($block.insts != null);
    		body = new MethodBody($block.decls,$block.insts);
    		setLocation(body,$block.start);
        }
      | ASM OPARENT code=multi_line_string CPARENT SEMI {
      		assert($code.text != null);
      		StringLiteral str = new StringLiteral($code.text.substring(1,$code.text.length()-1));
      		setLocation(str,$code.start);
      		body = new MethodAsm(str);	
      		setLocation(body,$code.start);
        }
      ) {	assert(body != null);
      		assert($type.tree != null);
    		assert($ident.tree != null);
    		assert($params.tree != null);
      		$tree = new DeclMethod($ident.tree,$type.tree,$params.tree,body);    		
    		setLocation($tree,$type.start);    		
        }
    ;

list_params returns[ListDeclParam tree]
@init{
	$tree = new ListDeclParam();
}
    : (p1=param {
    		assert($p1.tree != null);
    		$tree.add($p1.tree);
    		setLocation($tree,$p1.start);
        } (COMMA p2=param {
        	assert($p2.tree != null);
        	$tree.add($p2.tree);
        }
      )*)?
    ;

multi_line_string returns[String text, Location location]
    : s=STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    | s=MULTI_LINE_STRING {
            $text = $s.text;
            $location = tokenLocation($s);
        }
    ;

param returns[DeclParam tree]
    : type ident {
    		assert($type.tree != null);
    		assert($ident.tree != null);
    		$tree = new DeclParam($type.tree,$ident.tree);
       		setLocation($tree,$type.start);
        }
        
    ;