import os
import cv2

# Define paths
dataset_path = r"C:\Users\mophi\Downloads\animals"  # Path to your dataset
output_path = r"C:\Users\mophi\Downloads\yolo_dataset"  # Path to save YOLO dataset

# Create output directories
os.makedirs(os.path.join(output_path, "images"), exist_ok=True)
os.makedirs(os.path.join(output_path, "labels"), exist_ok=True)

# Get list of classes (folder names)
classes = sorted(os.listdir(dataset_path))

# Create a class-to-ID mapping
class_to_id = {class_name: idx for idx, class_name in enumerate(classes)}

# Process each class folder
for class_name in classes:
    class_folder = os.path.join(dataset_path, class_name)
    if not os.path.isdir(class_folder):
        continue  # Skip non-directory files (e.g., name of the animals.txt)

    # Process each image in the class folder
    for image_name in os.listdir(class_folder):
        image_path = os.path.join(class_folder, image_name)
        if not image_name.lower().endswith((".jpg", ".jpeg", ".png")):
            continue  # Skip non-image files

        # Load the image to get its dimensions
        image = cv2.imread(image_path)
        if image is None:
            print(f"Error loading image: {image_path}")
            continue
        height, width, _ = image.shape

        # Create a bounding box for the entire image (assuming one object per image)
        x_center = 0.5  # Center of the image
        y_center = 0.5  # Center of the image
        box_width = 1.0  # Full width of the image
        box_height = 1.0  # Full height of the image

        # Create the annotation line
        class_id = class_to_id[class_name]
        annotation_line = f"{class_id} {x_center} {y_center} {box_width} {box_height}"

        # Save the image to the output images folder
        output_image_path = os.path.join(output_path, "images", image_name)
        os.rename(image_path, output_image_path)

        # Save the annotation to the output labels folder
        annotation_name = os.path.splitext(image_name)[0] + ".txt"
        annotation_path = os.path.join(output_path, "labels", annotation_name)
        with open(annotation_path, "w") as f:
            f.write(annotation_line)

print("Dataset conversion complete!")