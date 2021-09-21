# Auteur : g27
# Version initiale : 14/01/2021

# Script de verification des tests contextuels. 
# Les tests lexicaux s'étant bien déroulés, on vérifie 
# que la sortie est bonne (sortie obtenue lors des tests précédents)
# /!!\ ne faire que lorsque les tests en question ont deja ete valides
#	precedemment /!!\

#On se place dans le repertoire du projet
cd "$(dirname "$0")"/../../.. || exit 1

PATH=./src/test/script/launchers:"$PATH"

#tests valides
for i in ./src/test/deca/context/valid/*.deca
do
	test_context "$i" > temp.res 2>&1
	VAR="${i%/*}"/resultats/"${i##*/}"
	if cmp -s $"temp.res" $""${VAR%.deca}"-cont.res"
	then
		echo -e "$i \e[1;32m ok\e[0m"
	else
		echo -e "$i \e[1;31m not ok\e[0m"
	fi
	rm temp.res
done

#tests invalides
for i in ./src/test/deca/context/invalid/*.deca
do
	test_context "$i" > temp.res 2>&1
	VAR="${i%/*}"/resultats/"${i##*/}"
	if cmp $"temp.res" $""${VAR%.deca}"-cont.res"
	then
		echo -e "$i \e[1;32m ok\e[0m"
	else
		echo -e "$i \e[1;31m not ok\e[0m"
	fi
	rm temp.res
done
