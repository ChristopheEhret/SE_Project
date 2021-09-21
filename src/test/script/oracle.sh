# Auteur : g27
# Version initiale : 14/01/2021

# Script de verification de tous les tests.
# Les tests lexicaux s'étant bien déroulés, on vérifie 
# que la sortie est bonne (sortie obtenue lors des tests précédents)
# /!!\ ne faire que lorsque les tests en question ont deja ete valides
#	precedemment /!!\

#On se place dans le repertoire du projet
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

./src/test/script/oracle-lex.sh
./src/test/script/oracle-synt.sh
./src/test/script/oracle-cont.sh
./src/test/script/oracle-ima.sh
