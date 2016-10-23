if [ ! -e images-test/baboon_gray.pgm ]; then
  convert -depth 8 -size 512x512 -compress none images-test/baboon_gray.png images-test/baboon_gray.pgm
fi

echo "*****************************"
echo "Test sur Baboon_gray.pgm"
echo ""
make exec FILE=images-test/baboon_gray.pgm

echo "*****************************"
echo "Test sur 1pix.pgm"
echo ""
make exec FILE=images-test/1pix.pgm

echo "*****************************"
echo "Test sur fin-palettes.pgm"
echo ""
make exec FILE=images-test/fin-palettes.pgm

echo "*****************************"
echo "Test sur init-palettes.pgm"
echo ""
make exec FILE=images-test/init-palettes.pgm
