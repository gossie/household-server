TEMP=$(getopt -n "$0" -a -l "image-id:,version:" -- -- "$@")

    [ $? -eq 0 ] || exit

    eval set -- "$TEMP"

    while [ $# -gt 0 ]
    do
        case "$1" in
            --image-id) IMAGE_ID="$2"; shift;;
            --version) VERSION="$2"; shift;;
        esac
        shift;
    done
    echo "Image ID: $IMAGE_ID";
    echo "VERSION: $VERSION";

docker tag $IMAGE_ID ghcr.io/gossie/household-app:$VERSION
docker push ghcr.io/gossie/household-app:$VERSION
