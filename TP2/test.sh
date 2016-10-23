if [ ! -e images-test/baboon_gray.pgm ]; then
  convert -depth 8 -size 512x512 -compress none images-test/baboon_gray.png images-test/baboon_gray.pgm
fi

sed -i".bak" '/#/d' images-test/baboon_gray.pgm > /dev/null
rm images-test/*.bak

echo "*****************************"
echo "Test sur Baboon_gray.pgm"
echo ""
make exec FILE=images-test/baboon_gray.pgm COLORS=3 > images-test/baboon_result.pgm
echo "make exec FILE=images-test/baboon_gray.pgm COLORS=3 > images-test/baboon_result.pgm"

echo -e "\n\n*****************************"
echo "Test sur 1pix.pgm"
echo ""
make exec FILE=images-test/1pix.pgm COLORS=1

echo -e "\n\n*****************************"
echo "Test sur fin-palettes.pgm"
echo ""
make exec FILE=images-test/fin-palettes.pgm COLORS=3

echo -e "\n\n*****************************"
echo "Test sur init-palettes.pgm"
echo ""
make exec FILE=images-test/init-palettes.pgm COLORS=3

echo ""
