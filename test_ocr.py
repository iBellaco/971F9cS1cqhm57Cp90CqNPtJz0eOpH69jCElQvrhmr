import sys

def do_ocr(image_path):
    import io
    from google.cloud import vision
    client = vision.ImageAnnotatorClient()
    with io.open(image_path, 'rb') as image_file:
        content = image_file.read()
    image = vision.Image(content=content)
    response = client.text_detection(image=image)
    texts = response.text_annotations
    if texts:
        print("Full text:\n", texts[0].description)
        for text in texts[1:]:
            print(f"Word: '{text.description}' bounds: {[(v.x, v.y) for v in text.bounding_poly.vertices]}")
    else:
        print("No text found")
