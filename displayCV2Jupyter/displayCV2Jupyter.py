import math
import cv2
import matplotlib.pyplot as plt


# img should be a single CV2 image
# title: title of te plot
def displayImage(img, title=None, size=(10, 10)):
    plt.figure(figsize=size)
    plt.imshow(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
    plt.title(title)
    plt.axis('off')
    plt.show()

# imgs should be a list of CV2 images
# title: title of te plot
def displayImages(imgs, titles=None, size=(15, 15)):
    num = len(imgs)
    subplotnum = math.floor(math.sqrt(num)) + 1 # making a grid of images
    plt.figure(figsize=size)
    
    for idx, img in enumerate(imgs):
        ax = plt.subplot(subplotnum, subplotnum, idx+1)
        im = ax.imshow(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
        if titles is not None:
            plt.title(titles[idx])
        plt.axis('off')
    
    plt.tight_layout()
    plt.show()
