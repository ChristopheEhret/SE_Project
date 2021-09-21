#! /bin/sh

# Auteur : Adrien Kaufman
# Version initiale : 12/01/2021

# Deuxieme script de test pour l'analyseur lexical 
# Lance tous les tests syntaxiquement valides avec 
# l'analyseur lexical
# Redirige la sortie standard et les erreurs dans un meme fichier

# On se place dans le repertoire du projet :
cd "$(dirname "$0")"/../../.. || exit 1

PATH=.src/test/script/launchers:"$PATH"

#tests valides lexicalement
echo "Analyseur lexical : tests valides"
for i in ./src/test/deca/syntax/valid/*.deca
do
	echo "$i"
	./src/test/script/launchers/test_lex "$i" > "${i%.deca}"-lex.res 2>&1
done
