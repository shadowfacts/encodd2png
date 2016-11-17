# compress2png
Encode and store files as PNG images.

## About
Stores 4 bytes per pixel + 1 extra pixel for metadata. Images use the smallest possible square size.

The encode2png 0.1.0 jar encoded into a PNG:

![](https://raw.githubusercontent.com/shadowfacts/encode2png/master/example.png)

## Download
Downloads are available on the [releases page](https://github.com/shadowfacts/compress2png/releases/).

## Usage
### Encode
```
java -jar compress2png-0.1.0.jar -m=encode -i=<input file> -o=<output image>
```
### Decode
```
java -jar compress2png-0.1.0.jar -m=encode -i=<input image> -o=<output file>
```
