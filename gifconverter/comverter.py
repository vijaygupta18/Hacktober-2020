import imageio 
import os
clip = os.path.abspath('main.mp4')

def gifmake(inputPath, targetFormat):
    outPath = os.path.splitext(inputPath)[0] + targetFormat

    print(f'converting {inputPath} \n to {outPath}')
    reader = imageio.get_reader(inputPath)
    fps = reader.get_meta_data()['fps']

    writer = imageio.get_writer(outPath, fps=fps)

    for frames in reader:
        writer.append_data(frames)
        print(f'frame {frames}')
    print('Done!!!')
    writer.close()

gifmake(clip, '.gif')