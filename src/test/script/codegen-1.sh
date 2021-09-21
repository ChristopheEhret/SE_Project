#! /bin/sh

# Auteur : Adrien Kaufman
# Version initiale : 12/01/2021

# Script de test pour les programmes deca valides
# Redirige la sortie standard et les erreurs dans un meme fichier

# On se place dans le repertoire du projet :
cd "$(dirname "$0")"/../../.. || exit 1

PATH=.src/test/script/launchers:"$PATH"

#tests valides lexicalement
echo "Generation de code : tests valides"
for i in ./src/test/deca/codegen/valid/*.deca
do
	echo "$i"
    ./src/main/bin/decac "$i"
    ima "${i%deca}"ass
    rm "${i%deca}"ass
done
