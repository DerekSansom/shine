package shine.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.AdMapper;
import shine.app.mapper.BrandMapper;
import shine.app.mapper.NoticeMapper;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Advert;
import com.shine.boards.CorpBrand;
import com.shine.boards.Notice;
import com.sp.advert.AdvertDao;
import com.sp.brand.BrandDao;
import com.sp.entity.CorpBrandEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ad.AdvertEntity;
import com.sp.img.ImageScaler;
import com.sp.notice.NoticeDao;
import com.sp.player.PlayerDao;

@Component
public class ImageHandler extends BaseHandler {

	private static final String IMG_TAG = ".png";

	private AdMapper adMapper = new AdMapper();
	private NoticeMapper noticeMapper = new NoticeMapper();
	private BrandMapper brandMapper = new BrandMapper();

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private AdvertDao advertDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private ImageScaler imageScaler;

	@Autowired
	private BrandDao brandDao;

	// @Transactional
	// public int handleUserImage(byte[] imagebytes, int userid) throws
	// ShineException {
	// PlayerEntity p = playerDao.getPlayerById(userid);
	// if (p == null) {
	// throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
	// }
	// saveImage(imagebytes, ShineProperties.getUserImageFolderPath(), userid);
	//
	// // if saved mark that icon exists in db
	// p.setHasIcon(true);
	// playerDao.save(p);
	//
	// return SharedConstants.SUCCESS;
	// }

	public String saveAdImage(InputStream is, int adid) throws IOException, ShineException {
		BufferedImage bufferedImage = imageScaler.scaleImage(is, ShineProperties.maxImageDimension());
		saveImage(bufferedImage, ShineProperties.getAdImageFolderPath(), adid);
		return adid + IMG_TAG;
	}

	@Transactional
	public void handleUserImage(BufferedImage bufferedImage, int userid) throws ShineException {
		PlayerEntity p = playerDao.getPlayerById(userid);
		if (p == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}
		saveImage(bufferedImage, ShineProperties.getUserImageFolderPath(), userid);

		// if saved mark that icon exists in db
		p.setHasIcon(true);
		playerDao.save(p);

	}

	private void saveImage(byte[] image, String path, int id) throws ShineException {

		File f = new File(path + id + IMG_TAG);
		OutputStream out;
		try {
			out = new FileOutputStream(f);
			out.write(image);
			out.close();

			log.debug("saved image id: " + id + ", image written to " + f.getAbsolutePath());
		} catch (IOException e) {
			log.error("Saving image", e);
			throw new ShineException(e);
		}

	}

	private String saveImage(BufferedImage bufferedImage, String path, int id) throws ShineException {

		File f = new File(path + id + IMG_TAG);

		try {
			ImageIO.write(bufferedImage, "png", f);

			log.debug("saved image id: " + id + ", image written to " + f.getAbsolutePath());
		} catch (IOException e) {
			log.error("Saving image", e);
			throw new ShineException(e);
		}
		return f.getAbsolutePath();
	}

	// @Transactional
	// public void handleAdImage(ByteArrayOutputStream imagebytes, int adid)
	// throws ShineException {
	//
	// AdvertEntity ad = advertDao.getAd(adid);
	// if (ad == null) {
	// throw new ShineException(GeneralError.NOT_FOUND);
	// }
	// saveImage(imagebytes.toByteArray(),
	// ShineProperties.getAdImageFolderPath(), adid);
	// ad.setImageUrl(adid + IMG_TAG);
	//
	// // if saved mark that icon exists in db
	// advertDao.saveOrUpdateAd(ad);
	//
	// }

	@Transactional
	public void handleNoticeImage(BufferedImage bufferedImage, int noticeid) throws ShineException {
		// saveImage(imagebytes, ShineProperties.getPostImageFolderPath(),
		// noticeid);
		NoticeEntity n = noticeDao.getNotice(noticeid);
		if (n == null) {
			throw new ShineException(GeneralError.NOT_FOUND, "Notice to upload image to is not found");
		}
		saveImage(bufferedImage, ShineProperties.getPostImageFolderPath(), noticeid);
		n.setImageUrl(noticeid + IMG_TAG);

		// if saved mark that icon exists in db
		noticeDao.createNotice(n);

	}

	@Transactional
	public void handleAdImage(BufferedImage bufferedImage, int adid) throws ShineException {

		AdvertEntity ad = advertDao.getAd(adid);
		if (ad == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		saveImage(bufferedImage, ShineProperties.getAdImageFolderPath(), adid);
		ad.setImageUrl(adid + IMG_TAG);

		// if saved mark that icon exists in db
		advertDao.saveOrUpdateAd(ad);

	}

	// @Transactional
	// public void handleNoticeImage(ByteArrayOutputStream imagebytes, int
	// noticeid) throws ShineException {
	// // saveImage(imagebytes, ShineProperties.getPostImageFolderPath(),
	// // noticeid);
	// NoticeEntity n = noticeDao.getNotice(noticeid);
	// if (n == null) {
	// throw new ShineException(GeneralError.NOT_FOUND,
	// "Notice to upload image to is not found");
	// }
	// saveImage(imagebytes.toByteArray(),
	// ShineProperties.getPostImageFolderPath(), noticeid);
	// n.setImageUrl(noticeid + IMG_TAG);
	//
	// // if saved mark that icon exists in db
	// noticeDao.createNotice(n);
	//
	//
	// }

	// @Transactional
	// public int createNoticeWithImage(byte[] image, Notice notice,
	// PlayerEntity p)
	// throws ShineException {
	//
	// NoticeEntity n = noticeMapper.dtoToEntity(notice);
	// n.setPlayer(p);
	// noticeDao.createNotice(n);
	//
	// if (n.getId() > 0) {
	// saveImage(image, ShineProperties.getPostImageFolderPath(), n.getId());
	// n.setImageUrl(n.getId() + IMG_TAG);
	// // if saved mark that icon exists in db
	// noticeDao.createNotice(n);
	// return n.getId();
	// } else {
	// throw new ShineException(GeneralError.SYSTEM_ERROR,
	// "Failed to create ad");
	// }
	//
	// }

	@Transactional
	public int createNoticeWithImage(BufferedImage bufferedImage, Notice notice, PlayerEntity p)
			throws ShineException {

		NoticeEntity n = noticeMapper.dtoToEntity(notice);
		n.setPlayer(p);
		noticeDao.createNotice(n);

		if (n.getId() > 0) {
			saveImage(bufferedImage, ShineProperties.getPostImageFolderPath(), n.getId());
			n.setImageUrl(n.getId() + IMG_TAG);
			// if saved mark that icon exists in db
			noticeDao.createNotice(n);
			return n.getId();
		} else {
			throw new ShineException(GeneralError.SYSTEM_ERROR, "Failed to create ad");
		}

	}

	@Transactional
	public int createAdWithImage(BufferedImage bufferedImage, Advert adDto) throws ShineException {

		AdvertEntity adEntity = adMapper.dtoToEntity(adDto);
		advertDao.saveOrUpdateAd(adEntity);
		if (adEntity.getId() > 0) {
			saveImage(bufferedImage,
					ShineProperties.getAdImageFolderPath(), adEntity.getId());
			adEntity.setImageUrl(adEntity.getId() + IMG_TAG);
			// if saved mark that icon exists in db
			advertDao.saveOrUpdateAd(adEntity);
			return adEntity.getId();
		} else {
			throw new ShineException(GeneralError.SYSTEM_ERROR,
					"Failed to create ad");
		}

	}

	@Transactional
	public int createBrandWithImages(BufferedImage bufferedImageBackground,
									 BufferedImage bufferedImageLogo,
									 CorpBrand brandDto) throws ShineException {

		CorpBrandEntity brandEntity = brandMapper.dtoToEntity(brandDto);
		brandDao.saveOrUpdateBrand(brandEntity);
		int brandId = brandEntity.getId();
		if (brandId > 0) {
			saveImage(	bufferedImageBackground,
						ShineProperties.getBrandBGfolderPath(), 
						brandId
						);
			brandEntity.setBackgroundimg(brandEntity.getId() + IMG_TAG);

			brandDao.saveOrUpdateBrand(brandEntity);
			saveImage(	bufferedImageLogo,
						ShineProperties.getLogoimgfolderPath(), 
						brandId
						);
			brandEntity.setBackgroundimg(brandId + IMG_TAG);

			brandDao.saveOrUpdateBrand(brandEntity);
			
			return brandId;
		} else {
			throw new ShineException(GeneralError.SYSTEM_ERROR,
					"Failed to create ad");
		}

	}

	// public void testMaxSize(InputStream stream) throws IOException,
	// ShineException {
	// // TODO probably don't want to do all this, or find a neater way
	// ByteArrayOutputStream imagebytes = WebAppUtils.streamToBytes(stream);
	// int size = imagebytes.size();
	// log.debug("process: imagesize = " + size);
	//
	// if (size > ShineProperties.maxImageSize()) {
	// throw new ShineException(GeneralError.ERROR_MAX_IMG_SIZE);
	//
	// }
	//
	// // stream.reset();
	// }


}
