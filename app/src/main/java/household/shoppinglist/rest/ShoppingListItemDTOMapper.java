package household.shoppinglist.rest;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import household.shoppinglist.domain.ShoppingListItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Component
class ShoppingListItemDTOMapper {

	ShoppingListItem map(ShoppingListItemDTO shoppingListItem) {
	    byte[] image = StringUtils.hasText(shoppingListItem.getImage()) ? Base64.getDecoder().decode(shoppingListItem.getImage()) : null;
	    if (image != null) {
	        image = scaleImage(image);
        }
		return new ShoppingListItem(null, shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}

    private byte[] scaleImage(byte[] image) {
        try (var in = new ByteArrayInputStream(image)) {
            var img = ImageIO.read(in);
            int width = 400;
            int height = (width * img.getHeight()) / img.getWidth();

            var scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            ImageIO.write(imageBuff, "png", buffer);

            return buffer.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ShoppingListItemDTO map(ShoppingListItem shoppingListItem) {
	    String image = shoppingListItem.getImage() != null ? Base64.getEncoder().encodeToString(shoppingListItem.getImage()) : null;
		return new ShoppingListItemDTO(shoppingListItem.getId(), shoppingListItem.getName(), shoppingListItem.isSelected(), image);
	}
}
